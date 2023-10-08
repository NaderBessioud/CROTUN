package tn.esprit.CROTUN.Controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import com.cloudinary.Cloudinary;
import com.stripe.exception.StripeException;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.CROTUN.Entities.Media;
import tn.esprit.CROTUN.Entities.Payement;
import tn.esprit.CROTUN.Entities.PaymentIntentDto;
import tn.esprit.CROTUN.Entities.reponse;
import tn.esprit.CROTUN.Services.CloudinaryService;
import tn.esprit.CROTUN.Services.PayementService;

@RestController
@CrossOrigin(origins = "*")
public class PaymentController {
	@Autowired
	CloudinaryService cl;
	@Autowired
	PayementService PS;
	
	
	@PostMapping("/addPayment")
	@ResponseBody
	public Payement addPayment(@RequestBody Payement p)
	{
		return PS.addPayement(p);
	}
	
	@PutMapping("/addSold/{id}/{code}")
	@ResponseBody
	public Double addSold(@PathVariable ("id") Long id,@PathVariable ("code") String code)
	{
		return PS.addSold(id,code);
	}
	
	@PostMapping("/Paye-With-Sold-card/{idc}/{ids}")
	@ResponseBody
	public reponse PayeeWith_credit_card(@PathVariable ("idc") Long idc,@PathVariable ("ids") Long ids)
	{
		return PS.PayeeWith_Sold_card(idc,ids);
	}
	
	@PostMapping("/image")
	@ResponseBody
	public void addImage(MultipartFile multipartFile) throws Exception 
	{
		
		

	
		 cl.upload(multipartFile);
		
	}
	
	
	@PostMapping("/paye_enligne/{idc}/{ids}")
	@ResponseBody
	public reponse paye_enligne(@PathVariable ("idc") Long idc,@PathVariable ("ids") Long ids,@RequestBody PaymentIntentDto pi) throws Exception 
	{
		
	return PS.Payee_Enligne(idc,ids, pi) ;
		
	}
}
