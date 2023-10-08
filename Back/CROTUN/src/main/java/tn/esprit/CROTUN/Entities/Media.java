package tn.esprit.CROTUN.Entities;



import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;




@Entity

public class Media implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long mediaId;
	
	
	
	private String name;
    private String imagenUrl;
    private String codeImage;
   
    @ManyToOne
    Slice slice;
    
	public Media(String name, String imagenUrl, String imagencode) {
	
		this.name = name;
		this.imagenUrl = imagenUrl;
		this.codeImage = imagencode;
	}

	public Long getMediaId() {
		return mediaId;
	}

	public void setMediaId(Long mediaId) {
		this.mediaId = mediaId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImagenUrl() {
		return imagenUrl;
	}

	public void setImagenUrl(String imagenUrl) {
		this.imagenUrl = imagenUrl;
	}

	public String getCodeImage() {
		return codeImage;
	}

	public void setCodeImage(String codeImage) {
		this.codeImage = codeImage;
	}

	public Slice getSlice() {
		return slice;
	}

	public void setSlice(Slice slice) {
		this.slice = slice;
	}
    
    
    
    
    
    

}