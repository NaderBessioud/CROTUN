package tn.esprit.CROTUN.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class ImagenCloud {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long IdImg;
	    private String name;
	    private String imagenUrl;
	    private String imagenId;

	    public ImagenCloud() {
	    }

	    public ImagenCloud(String name, String imagenUrl, String imagenId) {
	        this.name = name;
	        this.imagenUrl = imagenUrl;
	        this.imagenId = imagenId;
	    }

	    public Long getId() {
	        return IdImg;
	    }

	    public void setId(Long id) {
	        this.IdImg = id;
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

	    public String getImagenId() {
	        return imagenId;
	    }

	    public void setImagenId(String imagenId) {
	        this.imagenId = imagenId;
	    }
}
