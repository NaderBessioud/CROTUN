package tn.esprit.CROTUN.Services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import tn.esprit.CROTUN.Entities.CreditCard;
import tn.esprit.CROTUN.repository.*;


@Service
public class QRCodeGenerator {
	
	@Autowired
	CreditCardRepository cr;
	
	
	public ResponseEntity<?> generateQRCodeImage(Long id)
            throws WriterException, IOException {
		
		CreditCard aa = cr.findById(id).orElse(null);
		
		String QR_CODE_IMAGE_PATH = "C:\\CroTUN_Microfinance\\CROTUN\\src\\main\\resources\\qr\\"+aa.getCode()+".png";
		
		
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(aa.getCode(), BarcodeFormat.QR_CODE, 500, 500);

        Path path = FileSystems.getDefault().getPath(QR_CODE_IMAGE_PATH);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        aa.setQrcredit(QR_CODE_IMAGE_PATH);
        cr.save(aa);
       return ResponseEntity.ok(path);
    }
	
	
	public static byte[] getQRCodeImage(String text, int width, int height) throws WriterException, IOException {
	    QRCodeWriter qrCodeWriter = new QRCodeWriter();
	    BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
	    
	    ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
	    MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
	    byte[] pngData = pngOutputStream.toByteArray(); 
	    return pngData;
	}


}