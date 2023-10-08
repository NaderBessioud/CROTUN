package tn.esprit.CROTUN.Services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import tn.esprit.CROTUN.repository.ImagenCloudRepo;


@Service
public class CloudinaryService {
	 @Autowired
	 ImagenCloudRepo imagenRepository;
	 Cloudinary cloudinary;

	    private Map<String, String> valuesMap = new HashMap<>();

	    public CloudinaryService() {
	        valuesMap.put("cloud_name", "crotun");
	        valuesMap.put("api_key", "433815291151688");
	        valuesMap.put("api_secret", "Y3Mt47jaXTwVqYoGTbFrnq1bN7w");
	        cloudinary = new Cloudinary(valuesMap);
	    }

	    public Map upload(MultipartFile multipartFile) throws IOException {
	        File file = convert(multipartFile);
	        Map result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
	        file.delete();
	        return result;
	    }

	    public Map delete(String id) throws IOException {
	        Map result = cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
	        return result;
	    }

	    private File convert(MultipartFile multipartFile) throws IOException {
	        File file = new File(multipartFile.getOriginalFilename());
	        FileOutputStream fo = new FileOutputStream(file);
	        fo.write(multipartFile.getBytes());
	        fo.close();
	        return file;
	    }
}
