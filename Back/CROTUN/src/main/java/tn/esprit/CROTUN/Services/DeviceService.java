package tn.esprit.CROTUN.Services;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.conn.ConnectionRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;

import io.grpc.lb.v1.Server;
import tn.esprit.CROTUN.Entities.Agent;
import tn.esprit.CROTUN.Entities.Customer;
import tn.esprit.CROTUN.Entities.DeviceMetadata;
import tn.esprit.CROTUN.Entities.Investor;
import tn.esprit.CROTUN.Entities.Manager;
import tn.esprit.CROTUN.Event.MySimpleUrlAuthenticationSuccessHandler;
import tn.esprit.CROTUN.Repositories.AgentRepository;
import tn.esprit.CROTUN.Repositories.CustomerRepository;
import tn.esprit.CROTUN.Repositories.DeviceRepository;
import tn.esprit.CROTUN.Repositories.InvestorRespository;
import tn.esprit.CROTUN.Repositories.ManagerRepository;
import tn.esprit.CROTUN.Utils.HttpRequestResponseUtils;
import ua_parser.Client;
import ua_parser.Parser;

@Service
public class DeviceService {
	private static final String ALLOWED_URI_CHARS = "@#&=*+-_.,:!?()/~'%";
	private static final String UNKNOWN = "UNKNOWN";
	private final String FromAddress="CROTUN@gmail.com";
	private final String SenderName="CROTUN Team";
	private final RestTemplate restTemplate= new RestTemplate();
	
	private static final Logger logger = LogManager.getLogger(DeviceService.class);
	
	@Autowired
	private DeviceRepository deviceMetadataRepository;
	
	@Autowired
	 private JavaMailSender javaMailSender;
	
	
	
	@Autowired
	AgentRepository agentRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	ManagerRepository managerRepository;
	
	@Autowired
	InvestorRespository investorRespository;
	
	
    
   

	 private String parseXForwardedHeader(String header) {
	        return header.split(" *, *")[0];
	        
	    }
	 
	 private String extractIp(HttpServletRequest request) {
		 
		    String clientIp;
		    String clientXForwardedForIp = request
		      .getHeader("x-forwarded-for");
		    if (clientXForwardedForIp != null) {
		        clientIp = parseXForwardedHeader(clientXForwardedForIp);
		    } else {
		        clientIp = request.getRemoteAddr();
		    }
		    return clientIp;
		}
	 
	 private String getIpLocation(String ip) throws IOException, GeoIp2Exception {
		 
		    String location = UNKNOWN;
		    InetAddress ipAddress = InetAddress.getByName(ip);
		   
		    CityResponse cityResponse = getDatabaseReader()
		      .city(ipAddress);
		  
		        
		    if (Objects.nonNull(cityResponse)) {
		    	  
				    String url="https://nominatim.openstreetmap.org/reverse?format=jsonv2&lat="+cityResponse.getLocation().getLatitude()
				    		+"&lon="+cityResponse.getLocation().getLongitude();
				    JsonParser springParser = JsonParserFactory.getJsonParser();
				    Map< String, Object > map = springParser.parseMap(this.restTemplate.getForObject(url, String.class));
				    
				   
		    	
		        location = map.get("display_name").toString();
		    }    
		   
		    logger.info(location);
		    
		    return location;
		}
	 
	 private String getDeviceDetails(String userAgent) {
		    String deviceDetails = UNKNOWN;
		    
		    Client client = getParser().parse(userAgent);
		    logger.info(client);
		    if (Objects.nonNull(client)) {
		        deviceDetails = client.userAgent.family
		          + " " + client.userAgent.major + "." 
		          + client.userAgent.minor + " - "
		          + client.os.family + " " + client.os.major
		          + "." + client.os.minor; 
		    }
		    
		    logger.info(deviceDetails);
		    return deviceDetails;
		}
	 
	 public void verifyAgentDevice(Agent user, HttpServletRequest request)throws IOException, GeoIp2Exception {
		    
		    String ip = extractIp(request);
		    String location = getIpLocation(ip);

		    String deviceDetails = HttpRequestResponseUtils.getUserAgent();
		        
		    DeviceMetadata existingDevice
		      = findExistingDevice(user, deviceDetails, location);
		        
		    if (Objects.isNull(existingDevice)) {
		        unknownDeviceNotification(deviceDetails, location,
		          ip, user.getEmail(), request.getLocale(),user.getIdA(),"agent");

		      /*  DeviceMetadata deviceMetadata = new DeviceMetadata();
		        deviceMetadata.setAgentDevice(user);
		        deviceMetadata.setLocation(location);
		        deviceMetadata.setDeviceDetails(deviceDetails);
		        deviceMetadata.setLastLoggedIn(new Date());
		        deviceMetadataRepository.save(deviceMetadata);*/
		    } else {
		        existingDevice.setLastLoggedIn(new Date());
		        deviceMetadataRepository.save(existingDevice);
		    }
		}
	 
	 
	 
	 public void verifyCustomerDevice(Customer user, HttpServletRequest request)throws IOException, GeoIp2Exception {
		    
		    String ip = extractIp(request);
		    String location = getIpLocation(ip);

		    String deviceDetails = getDeviceDetails(request.getHeader("user-agent"));
		        
		    DeviceMetadata existingDevice
		      = findExistingDevice(user, deviceDetails, location);
		        
		    if (Objects.isNull(existingDevice)) {
		        unknownDeviceNotification(deviceDetails, location,
		          ip, user.getEmail(), request.getLocale(),user.getIdC(),"customer");

		       /* DeviceMetadata deviceMetadata = new DeviceMetadata();
		        deviceMetadata.setCustomerDevice(user);
		        deviceMetadata.setLocation(location);
		        deviceMetadata.setDeviceDetails(deviceDetails);
		        deviceMetadata.setLastLoggedIn(new Date());
		        deviceMetadataRepository.save(deviceMetadata);*/
		    } else {
		        existingDevice.setLastLoggedIn(new Date());
		        deviceMetadataRepository.save(existingDevice);
		    }
		}
	 
	 
	 public void verifyManagerDevice(Manager user, HttpServletRequest request) throws IOException, GeoIp2Exception {
		    
		    String ip = extractIp(request);
		    String location = getIpLocation(ip);

		    String deviceDetails = getDeviceDetails(request.getHeader("user-agent"));
		        
		    DeviceMetadata existingDevice
		      = findExistingDevice(user, deviceDetails, location);
		        
		    if (Objects.isNull(existingDevice)) {
		        unknownDeviceNotification(deviceDetails, location,
		          ip, user.getEmail(), request.getLocale(),user.getIdM(),"manager");
/*
		        DeviceMetadata deviceMetadata = new DeviceMetadata();
		        deviceMetadata.setManagerDevice(user);
		        deviceMetadata.setLocation(location);
		        deviceMetadata.setDeviceDetails(deviceDetails);
		        deviceMetadata.setLastLoggedIn(new Date());
		        deviceMetadataRepository.save(deviceMetadata);*/
		    } else {
		        existingDevice.setLastLoggedIn(new Date());
		        deviceMetadataRepository.save(existingDevice);
		    }
		}
	 
	 public void verifyInvestorDevice(Investor user, HttpServletRequest request)throws IOException, GeoIp2Exception {
		    
		    String ip = extractIp(request);
		    String location = getIpLocation(ip);

		    String deviceDetails = getDeviceDetails(request.getHeader("user-agent"));
		        
		    DeviceMetadata existingDevice
		      = findExistingDevice(user, deviceDetails, location);
		        
		    if (Objects.isNull(existingDevice)) {
		        unknownDeviceNotification(deviceDetails, location,
		          ip, user.getEmail(), request.getLocale(),user.getIdInvestor(),"investor");

		       /* DeviceMetadata deviceMetadata = new DeviceMetadata();
		        deviceMetadata.setInvestorDevice(user);
		        deviceMetadata.setLocation(location);
		        deviceMetadata.setDeviceDetails(deviceDetails);
		        deviceMetadata.setLastLoggedIn(new Date());
		        deviceMetadataRepository.save(deviceMetadata);*/
		    } else {
		        existingDevice.setLastLoggedIn(new Date());
		        deviceMetadataRepository.save(existingDevice);
		    }
		}
	 
	 
	 
	 
	 
	 private DeviceMetadata findExistingDevice(
			  Object user, String deviceDetails, String location) {
		 List<DeviceMetadata> knownDevices;
		 	if(user instanceof Agent)
			     knownDevices
			      = deviceMetadataRepository.findByAgentId(((Agent) user).getIdA());
		 	else if(user instanceof Customer)
		 		 knownDevices
			      = deviceMetadataRepository.findByCustomerId(((Customer) user).getIdC());
		 	else if(user instanceof Manager)
		 		 knownDevices
			      = deviceMetadataRepository.findByManagerId(((Manager)user).getIdM());
		 	else
		 		knownDevices
			      = deviceMetadataRepository.findByInvestorId(((Investor)user).getIdInvestor());
		 	
			    for (DeviceMetadata existingDevice : knownDevices) {
			        if (existingDevice.getDeviceDetails().equals(deviceDetails)) {
			            return existingDevice;
			        }
			    }
			    return null;
			}
	 
	 private void unknownDeviceNotification(String deviceDetails, String location, String ip, String email, Locale locale,Long id,String type)  {
	     System.out.print(email);  
		 final String subject = "New Login Notification";
	        String content = "<p>Hello,</p>"
		            + "<p>A new device is connected to your account near [[loc]]</p>"
		            + "<p>Click the link below to confirm this device:</p>"
		            + "<p><a href=\"[[URL]]\" >confirm device</a></p>"
		            + "<br>";
		           
	        MimeMessage notification = javaMailSender.createMimeMessage(); 
	        MimeMessageHelper helper = new MimeMessageHelper(notification);
	        String verifyURL;
	        String loc="";
	        try {
	        	loc=location.replace(" ", "+");
				 loc=URLEncoder.encode(location,"UTF-8");
				 System.out.print("haddhi location   "+loc);
				 
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
	        if(type.equals("agent")) {
	        	
	         verifyURL = "http://localhost:4200/DeviceAct?deviceDetails=" + deviceDetails.replace(" ", "+")+"&location="+loc+"&username="+agentRepository.findById(id).get().getUserName()+"&ip="+ip+"&type=agent";
	        }
	        else if(type.equals("customer")) {
	        	 verifyURL = "http://localhost:4200/DeviceAct?deviceDetails=" + deviceDetails.replace(" ", "+")+"&location="+loc+"&username="+customerRepository.findById(id).get().getUserName()+"&ip="+ip+"&type=customer";
	        }
	        else if(type.equals("investor")) {
	        	 verifyURL = "http://localhost:4200/DeviceAct?deviceDetails=" + deviceDetails.replace(" ", "+")+"&location="+loc+"&username="+investorRespository.findById(id).get().getUserName()+"&ip="+ip+"&type=investor";
	        }
	        else {
	        	 verifyURL = "http://localhost:4200/DeviceAct?deviceDetails=" + deviceDetails.replace(" ", "+")+"&location="+loc+"&username="+managerRepository.findById(id).get().getUserName()+"&ip="+ip+"&type=manager";
	        }
	        	
	        
	        content = content.replace("[[URL]]", verifyURL);
	        content = content.replace("[[loc]]", location);
	        try {
				helper.setFrom(FromAddress,SenderName);
				   helper.setTo(email);
			        helper.setSubject(subject);
			        helper.setText(content, true);
			} catch (UnsupportedEncodingException | MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     
	        javaMailSender.send(notification);

	    } 
	 
	 public DeviceMetadata confirmAgentDevice(String username,String details,String location1) {
		 
		 Agent agent=agentRepository.findByUserName(username);
		 DeviceMetadata deviceMetadata = new DeviceMetadata();
		 details=details.replace("+", " ");
		 location1=location1.replace("+", " ");
	     deviceMetadata.setAgentDevice(agent);
	     deviceMetadata.setLocation(location1);
	     deviceMetadata.setDeviceDetails(details);
	     deviceMetadata.setLastLoggedIn(new Date());
	     deviceMetadataRepository.save(deviceMetadata);
	     return deviceMetadata;
	 }
	 
	 
	 public DeviceMetadata confirmCustomerDevice(String username,String details,String location) {
		 Customer customer=customerRepository.findByUserName(username);
		 details=details.replace("+", " ");
		 location=location.replace("+", " ");
		 DeviceMetadata deviceMetadata = new DeviceMetadata();
	     deviceMetadata.setCustomerDevice(customer);
	     deviceMetadata.setLocation(location);
	     deviceMetadata.setDeviceDetails(details);
	     deviceMetadata.setLastLoggedIn(new Date());
	     deviceMetadataRepository.save(deviceMetadata);
	     return deviceMetadata;
	 }
	 
	 public DeviceMetadata confirmManagerDevice(String username,String details,String location) {
		 Manager manager=managerRepository.findByUserName(username);
		 details=details.replace("+", " ");
		 location=location.replace("+", " ");
		 DeviceMetadata deviceMetadata = new DeviceMetadata();
	     deviceMetadata.setManagerDevice(manager);
	     deviceMetadata.setLocation(location);
	     deviceMetadata.setDeviceDetails(details);
	     deviceMetadata.setLastLoggedIn(new Date());
	     deviceMetadataRepository.save(deviceMetadata);
	     return deviceMetadata;
	 }
	 
	 public DeviceMetadata confirmInvestorDevice(String username,String details,String location) {
		 Investor investor=investorRespository.findByUserName(username);
		 details=details.replace("+", " ");
		 location=location.replace("+", " ");
		 DeviceMetadata deviceMetadata = new DeviceMetadata();
	     deviceMetadata.setInvestorDevice(investor);
	     deviceMetadata.setLocation(location);
	     deviceMetadata.setDeviceDetails(details);
	     deviceMetadata.setLastLoggedIn(new Date());
	     deviceMetadataRepository.save(deviceMetadata);
	     return deviceMetadata;
	 }
	 
	 

	
	   
	
@Bean
public DatabaseReader getDatabaseReader() {
	
	File citytDatabase = new File("C:\\Users\\ASUS\\OneDrive\\Bureau\\PIDEV\\CroTUN_Microfinance\\CROTUN\\src\\main\\resources\\GEO\\GeoLite2-City.mmdb");
	
	try {
		DatabaseReader citytDatabaseReader = new DatabaseReader.Builder(citytDatabase).build();
		return citytDatabaseReader;
	} catch (IOException e) {
		
		e.printStackTrace();
		return null;
	}
	
	
}

@Bean
public Parser getParser() {
	try {
		return new Parser();
	} catch (IOException e) {
		System.out.print("mochla okhra");
		e.printStackTrace();
		return null;
	}
}
}
