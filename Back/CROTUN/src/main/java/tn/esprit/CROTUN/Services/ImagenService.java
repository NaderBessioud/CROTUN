package tn.esprit.CROTUN.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.CROTUN.Entities.ImagenCloud;
import tn.esprit.CROTUN.Entities.Media;
import tn.esprit.CROTUN.repository.ImagenCloudRepo;
import tn.esprit.CROTUN.*;


@Service
@Transactional
public class ImagenService {
	 @Autowired
	 ImagenCloudRepo imagenRepository;

	  


	    public Optional<Media> getOne(Long id){
	        return imagenRepository.findById(id);
	    }

	    public void save(Media media){
	    	imagenRepository.save(media);
	    }

	    public void delete(Long id){
	    	imagenRepository.deleteById(id);
	    }

	    public boolean exists(Long id){
	        return imagenRepository.existsById(id);
	    }
}
