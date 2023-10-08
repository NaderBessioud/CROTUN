package tn.esprit.CROTUN.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.CROTUN.Entities.ImagenCloud;
import tn.esprit.CROTUN.Entities.Media;

@Repository
public interface ImagenCloudRepo extends JpaRepository<Media, Long>{

}
