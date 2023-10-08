package tn.esprit.CROTUN.Controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import tn.esprit.CROTUN.Entities.Customer;
import tn.esprit.CROTUN.Entities.Media;
import tn.esprit.CROTUN.Entities.OcrModel;
import tn.esprit.CROTUN.repository.*;

import tn.esprit.CROTUN.Entities.Customer;
import tn.esprit.CROTUN.Entities.Slice;
import tn.esprit.CROTUN.Entities.reponse;
import tn.esprit.CROTUN.Repositories.CustomerRepository;
import tn.esprit.CROTUN.Repositories.SliceRepository;
import tn.esprit.CROTUN.Services.CloudinaryService;
import tn.esprit.CROTUN.Services.ImagenService;


@RestController
@CrossOrigin(origins = "*")
public class scanner {

@Autowired
CustomerRepository cr;
@Autowired
SliceRepository sr;
@Autowired
CloudinaryService cloudImage;
@Autowired
ImagenService imagenService;
	@PostMapping("/ocr")

	public String DoOCR(@RequestParam("Image") MultipartFile image) throws IOException {

		
		OcrModel request = new OcrModel();
		request.setDestinationLanguage("fra");
		request.setImage(image);

		ITesseract instance = new Tesseract();

		try {
			
			BufferedImage in = ImageIO.read(convert(image));

			BufferedImage newImage = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_ARGB);
            
			Graphics2D g = newImage.createGraphics();
			g.drawImage(in, 0, 0, null);
			g.dispose();
            
			instance.setLanguage(request.getDestinationLanguage());
			instance.setDatapath("..\\CROTUN\\tessdata");

			String result = instance.doOCR(newImage);
			
			
		   
			
			return result;
			
			
			
			

			
		

		} catch (TesseractException | IOException e) {
			System.err.println(e.getMessage());
			return "Error while reading image";
		}

		
	}
	
	public static File convert(MultipartFile file) throws IOException {
	    File convFile = new File(file.getOriginalFilename());
	    convFile.createNewFile();
	    FileOutputStream fos = new FileOutputStream(convFile);
	    fos.write(file.getBytes());
	    fos.close();
	    return convFile;
	}
	
	@PostMapping("/Verifier-recu/{id}/{idSlice}")
	  public   reponse affiche(
				@RequestParam("Image") MultipartFile image,@PathVariable Long id,@PathVariable Long idSlice) throws IOException{
		  
		String  result = DoOCR(image) ;
		 Boolean z=false;
		
		   String text1=result.split("\\'")[1];
		  String text2=result.split("\\'")[5];
			String text3=result.split("\\'")[7];
			String text4=result.split("\\'")[9];
			Customer customer =cr.findById(id).orElse(null);
			Slice slice = sr.findById(idSlice).orElse(null);
			if(slice.getVerified()==false) {
			
			
				
			
						 if(text2.contains(customer.getCIN())&&(text3.contains(Double.toString((slice.getPrice())))&&(text4.contains(Integer.toString(slice.getRip())))
								 &&(text1.contains(slice.getReference())) ))
						 {
							 
							 slice.setVerified(true);
					        
							sr.save(slice);
							
							
							BufferedImage bi = ImageIO.read(image.getInputStream());
							
							Map resultMap = cloudImage.upload(image);
							
							Media media = new Media((String) 
									resultMap.get("original_filename")
									, (String) resultMap.get("url"),
									(String) resultMap.get("public_id"));
							media.setSlice(slice);
							imagenService.save(media);
							
							
							
							return new reponse("verification validate avec succes ");
					}
						 
			return new reponse("recue refusee");
			
			}
			
return new reponse("slice deja payee");	
}
			
		
		
		
			
}
		
		
			
	/*		List<String>texts=new ArrayList<>();
			texts.add(text1);
			texts.add(text2);
			texts.add(text3);
			texts.add(text4);
			
			return texts;
		  
	  */
	
		
	


