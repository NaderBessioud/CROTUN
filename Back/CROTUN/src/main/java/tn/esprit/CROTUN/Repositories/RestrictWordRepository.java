package tn.esprit.CROTUN.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.CROTUN.Entities.RestrictWord;


@Repository
public interface RestrictWordRepository extends CrudRepository<RestrictWord, Integer> {

	public RestrictWord findById(Long id);
	public RestrictWord findByWord(String word);
	public List<RestrictWord> findAll();	
	
}


