package tn.esprit.CROTUN.Controller;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties.Tomcat.Resource;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.function.EntityResponse;

import io.netty.handler.codec.http.HttpRequest;

import java.awt.Image;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import tn.esprit.CROTUN.Entities.Agent;
import tn.esprit.CROTUN.Entities.Customer;
import tn.esprit.CROTUN.Entities.DeviceMetadata;
import tn.esprit.CROTUN.Entities.ImageResponse;
import tn.esprit.CROTUN.Entities.Investor;
import tn.esprit.CROTUN.Entities.JwtRequest;
import tn.esprit.CROTUN.Entities.JwtResponse;
import tn.esprit.CROTUN.Entities.Manager;
import tn.esprit.CROTUN.Entities.PasswordResetToken;
import tn.esprit.CROTUN.Entities.RefreshToken;
import tn.esprit.CROTUN.Entities.TokenRefreshRequest;
import tn.esprit.CROTUN.Entities.UserActivity;
import tn.esprit.CROTUN.Event.MySimpleUrlAuthenticationSuccessHandler;
import tn.esprit.CROTUN.Event.OnRegistrationCompleteEvent;
import tn.esprit.CROTUN.Exception.TokenRefreshException;
import tn.esprit.CROTUN.Exception.UserNotFoundException;
import tn.esprit.CROTUN.Services.ActivityService;
import tn.esprit.CROTUN.Services.AgentService;
import tn.esprit.CROTUN.Services.AzureBlobAdapter;
import tn.esprit.CROTUN.Services.DeviceService;
import tn.esprit.CROTUN.Services.IAgentService;
import tn.esprit.CROTUN.Services.ICustomerService;
import tn.esprit.CROTUN.Services.IEmailVerificationTokenService;
import tn.esprit.CROTUN.Services.IInvestorService;
import tn.esprit.CROTUN.Services.IManagerService;
import tn.esprit.CROTUN.Services.IPasswordTokenService;
import tn.esprit.CROTUN.Services.IRefreshTokenService;
import tn.esprit.CROTUN.security.JWTTokenProvider;
import tn.esprit.CROTUN.security.UserPrincipal;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthenticationController {
	
	private static final Logger logger=LogManager.getLogger(AuthenticationController.class);
	
	
	private final RestTemplate restTemplate= new RestTemplate();
	@Autowired
	IAgentService agentService;
	
	@Autowired
	ICustomerService custService;
	
	@Autowired
	IManagerService managerService;
	
	@Autowired
	IInvestorService investorService;
	
	@Autowired
	JWTTokenProvider jwtTokenProvider;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private IRefreshTokenService refreshTokenService;
	
	@Autowired
	IPasswordTokenService passwordTokenService;
	
	@Autowired
	private IEmailVerificationTokenService emailVerificationTokenService;
	
	@Autowired
	ApplicationEventPublisher eventPublisher;
	
	 @Autowired
	 AzureBlobAdapter azureAdapter;
	 
	 @Autowired
	 MySimpleUrlAuthenticationSuccessHandler authenticationSuccessHandler;
	 
	 @Autowired
	 ActivityService activityService;
	 
	 @Autowired
	 DeviceService deviceService;
	 
	 
	
	
	
	//@Autowired
	//MySimpleUrlAuthenticationSuccessHandler authenticationSuccessHandler=new MySimpleUrlAuthenticationSuccessHandler(authenticationManager);

	 @GetMapping("/loginAgent")
	 @ResponseBody
	 public Agent loginAgent(@RequestParam("username") String username,HttpServletRequest request) {
		 Agent agent=agentService.findByUserName(username);
		 authenticationSuccessHandler.loginAgentNotification(agent, request);
		 UserPrincipal principal=new UserPrincipal(agent);
		String  token=jwtTokenProvider.generateToken(principal);
		  agent.setToken(token);
		  RefreshToken refreshToken=refreshTokenService.CreateRefreshToken(principal);
		  return agent;
	 }
	 
	
	@GetMapping("/login")
	@ResponseBody
	public ResponseEntity<?> Login( @RequestParam("username") String username,@RequestParam("pass") String pass,HttpServletRequest request) throws Exception {
		
	
		int result=0;
		
		
		result =authenticate(username,pass);
		
		if(result == 0) {
		
		Agent ag=agentService.findByUserName(username);

		Manager ma=managerService.getManagerByUsername(username);
		Customer cs=custService.getCustomerByusername(username);
		Investor investor=investorService.findByUserName(username);
		String token="";
		JwtResponse jwtResponse=null;
		RefreshToken refreshToken=null;
		
		UserPrincipal principal;
		
		if(ag != null) {
			//authenticationSuccessHandler.loginAgentNotification(ag, request);
			 principal=new UserPrincipal(ag);
			  token=jwtTokenProvider.generateToken(principal);
			  ag.setToken(token);
			  refreshToken=refreshTokenService.CreateRefreshToken(principal);
			  jwtResponse=new JwtResponse(token, refreshToken.getToken(), ag);
		}
		else if(ma != null) {
			//authenticationSuccessHandler.loginManagerNotification(ma,request);
			principal=new UserPrincipal(ma);
			token=jwtTokenProvider.generateToken(principal);
			 ma.setToken(token);
			 refreshToken=refreshTokenService.CreateRefreshToken(principal);
			 jwtResponse=new JwtResponse(token, refreshToken.getToken(), ma);
		}
		else if(investor != null) {
			//authenticationSuccessHandler.loginInvestorNotification(investor, request);
			principal=new UserPrincipal(investor);
			token=jwtTokenProvider.generateToken(principal);
			investor.setToken(token);
			 refreshToken=refreshTokenService.CreateRefreshToken(principal);
			 jwtResponse=new JwtResponse(token, refreshToken.getToken(), investor);
		}
		else if(cs != null){
			//authenticationSuccessHandler.loginCustomerNotification(cs,request);
		principal=new UserPrincipal(cs);
		token=jwtTokenProvider.generateToken(principal);
		cs.setToken(token);
		refreshToken=refreshTokenService.CreateRefreshToken(principal);
		jwtResponse=new JwtResponse(token, refreshToken.getToken(), cs);
		}
		
		
		return ResponseEntity.ok(jwtResponse);
		}
		else 
			return ResponseEntity.ok(result);
		
	}
	
	
	
	
	private int authenticate(String username, String pass) throws Exception{
		try {
		SecurityContextHolder.getContext().setAuthentication(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, pass)));
			return 0;
			


			
		}
		catch (DisabledException ex) {
			return 1;
		}
		catch (BadCredentialsException ex) {
			
			return 2;
		}catch (Exception e) {
			return 3;
		}
		
	}
	
	
	@PostMapping("/refreshToken")
	public ResponseEntity<?> refreshToken(@Valid @RequestBody TokenRefreshRequest request){
		RefreshToken requestRefreshToken=request.getRefreshToken();
		RefreshToken tokenRefresh=refreshTokenService.getByToken(requestRefreshToken.getToken()).get();
		UserPrincipal principal=null;
		if(tokenRefresh != null && refreshTokenService.VerifyExpiration(tokenRefresh)) {
		
		if(tokenRefresh.getAgentToken() != null) {
			
			 principal=new UserPrincipal(tokenRefresh.getAgentToken());
		}
		else if(tokenRefresh.getManagerToken() != null) {
			
			principal=new UserPrincipal(tokenRefresh.getManagerToken());
		}
		else if(tokenRefresh.getInvestorToken()!= null) {
			principal=new UserPrincipal(tokenRefresh.getInvestorToken());
		}
		else if(tokenRefresh.getCustomerToken() != null) {
			
			principal=new UserPrincipal(tokenRefresh.getCustomerToken());
		}
		
		
		String token=jwtTokenProvider.generateToken(principal);
		return ResponseEntity.ok(new JwtResponse(token,tokenRefresh.getToken()));
	}
		else {
			throw new TokenRefreshException(requestRefreshToken.getToken(),"Refresh token is not in database!");
		}
			
	}
	
	@PostMapping("/addAgent")
	@ResponseBody
	public Agent registerAgent(@RequestBody Agent agent,@RequestParam("image") String image) {
		try {
			agent.setImage(image);
			agentService.addAgent(agent);
			eventPublisher.publishEvent(new OnRegistrationCompleteEvent( agent));
		} catch (Exception e) {
			System.out.print(e.getMessage());
			
		}
		
		return agent;
	}
	
	@PostMapping("/addCustomer")
	@ResponseBody
	public Customer registerCustomer(@RequestBody Customer customer,@RequestParam("image") String image) {
		try {
			customer.setImage(image);
			custService.addCustomer(customer);
			eventPublisher.publishEvent(new OnRegistrationCompleteEvent( customer));
			
			
			
		} catch (Exception e) {
			System.out.print(e.getMessage());
			
		}
		
		return customer;
	}
	
	
	@PostMapping("/addManager")
	@ResponseBody
	public Manager registerManager(@RequestBody Manager manager,@RequestParam("image") String image) {
		try {
			manager.setImage(image);
			managerService.addManager(manager);
			eventPublisher.publishEvent(new OnRegistrationCompleteEvent( manager));
			
		} catch (Exception e) {
			System.out.print(e.getMessage());
			
		}
		
		return manager;
	}
	
	@PostMapping("/addInvestor")
	@ResponseBody
	public Investor registerInvestor(@RequestBody Investor investor,@RequestParam("image") String image) {
		try {
			investor.setImage(image);
			investorService.addInvestor(investor);
			eventPublisher.publishEvent(new OnRegistrationCompleteEvent(investor));
			
		} catch (Exception e) {
			System.out.print(e.getMessage());
			
		}
		
		return investor;
	}
	
	
	
	@GetMapping("/confirmRegistration/{token}")
	
	public void confirmAgentRegistration(@PathVariable("token") String token) {
		
		emailVerificationTokenService.ConfirmUserRegistration(token);
	}
	
	
	
	@GetMapping("/verifyEmail")
	@ResponseBody
	public Boolean verifyEmail(@RequestParam("email") String email) {
		if ((agentService.findByEmail(email)!= null) || (custService.findByEmail(email)!= null)
				|| (managerService.findByEmail(email) != null) ||(investorService.findByEmail(email)!= null)) {
			logger.info("Email exist");
			return true;
		}
		else 
			
			return false;
		
			
		}
	
	
	@GetMapping("/verifyUsername")
	@ResponseBody
	public Boolean verifyUsername(@RequestParam("username") String username) {
		if ((agentService.findByUserName(username)!= null) || (custService.getCustomerByusername(username)!= null)
				|| (managerService.getManagerByUsername(username) != null) ||(investorService.findByUserName(username)!= null)) {
			logger.info("Username exist");
			return true;
		}
		else
			return false;
		}
	
	
	
	@GetMapping("/test")
	@ResponseBody
	public String text() {
		return custService.getTextFromImage("C:\\Users\\ASUS\\OneDrive\\Bureau\\CIN\\CIN.jpg");
	}
	
	
	 @PostMapping("/upload")
	    public  ImageResponse uploadFile(@RequestParam(value = "imageFile", required = true) MultipartFile files)  {
		 
	        String name = azureAdapter.upload(files, "prefix");
	       // Map<String, String> result = new HashMap<>();
	        //result.put("key", name);
	        return new ImageResponse(name);
	    }

	    
	    private ResponseEntity<byte[]> downloadFile( String file) throws IOException {
	        byte[] data = azureAdapter.getFile(file);
	        
	        ByteArrayResource resource = new ByteArrayResource(data);
	        String encodidImage=Base64.getEncoder().encodeToString(data);

	        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(data);
	        //return ResponseEntity.ok("ff");

	    }
	    
	    @GetMapping(value = "/download")
	    public ImageResponse download(@RequestParam("image") String file) throws IOException{
	    	
	    	byte[] bytes=this.downloadFile(file).getBody();
	    	String encodedImage=Base64.getEncoder().encodeToString(bytes);
	    	encodedImage="data:image/png;base64,"+encodedImage;
	    	System.out.println(encodedImage);
	    	
	    	
	    	return new ImageResponse(encodedImage);
	    }
	    
	    @GetMapping("/ConsultDeviceActivity")
	    @ResponseBody
	    public List<UserActivity> getDeviceActivity(@RequestParam("user") String user,@RequestParam("ip") String ip){
	    	return activityService.getUserDeviceActivity(user, ip);
	    }
	
	    
		@GetMapping("/resetPasswordRequest")
		@ResponseBody
		public PasswordResetToken generatePassToken(@RequestParam("email") String email) {
			try {
				return passwordTokenService.CreatePasswordToken(email);
			} catch (UnsupportedEncodingException |MessagingException e) {
				System.out.print(e.getMessage());
			}catch(UserNotFoundException ex) {
				System.out.print(ex.getMessage());
			}
			return null;
		}
		
		
		@GetMapping("/resetPassword")
		public void resetPasswod(@RequestParam("token") String token,@RequestParam("pass") String pass) {
			passwordTokenService.ConfirmPasswordReset(token, pass);
		}
		
		@GetMapping("/confirmAgentDevice")
		@ResponseBody
		public DeviceMetadata confirmAgentDevice(@RequestParam("details") String details,@RequestParam("loc") String loc,@RequestParam("username") String username) {
			return deviceService.confirmAgentDevice(username, details, loc);
		}
		
		@GetMapping("/confirmCustomerDevice")
		@ResponseBody
		public DeviceMetadata confirmCustomerDevice(@RequestParam("details") String details,@RequestParam("loc") String loc,@RequestParam("username") String username) {
			return deviceService.confirmCustomerDevice(username, details, loc);
		}
		
		@GetMapping("/confirmInvestorDevice")
		@ResponseBody
		public DeviceMetadata confirmInvestorDevice(@RequestParam("details") String details,@RequestParam("loc") String loc,@RequestParam("username") String username) {
			return deviceService.confirmInvestorDevice(username, details, loc);
		}
		
		
		@GetMapping("/confirmManagerDevice")
		@ResponseBody
		public DeviceMetadata confirmManagerDevice(@RequestParam("details") String details,@RequestParam("loc") String loc,@RequestParam("username") String username) {
			return deviceService.confirmManagerDevice(username, details, loc);
		}
		
		@PutMapping("/banAgent/{username}/{nbr}")
		@ResponseBody
		public Agent banAgent(@PathVariable("username") String username,@PathVariable("nbr") int nbr) {
			return agentService.banAgent(username, nbr);
			
		}
		
		@PutMapping("/updateAgent")
		@ResponseBody
		public Agent updateAgent(@RequestBody Agent agent) {
			try {
				agentService.updateAgent(agent);
				
			} catch (Exception e) {
				System.out.print(e.getMessage());
			}
			
			return agent;
		}
		
		@GetMapping("/getAddress")
		@ResponseBody
		public String getAddress(@RequestParam("lat") String lat,@RequestParam("lng") String lng) {
			 String url="https://nominatim.openstreetmap.org/reverse?format=jsonv2&lat="+lat
			    		+"&lon="+lng;
			    JsonParser springParser = JsonParserFactory.getJsonParser();
			    Map< String, Object > map = springParser.parseMap(this.restTemplate.getForObject(url, String.class));
			    
			   
	    	
	        return map.get("display_name").toString();
		}
	
	
	
}
