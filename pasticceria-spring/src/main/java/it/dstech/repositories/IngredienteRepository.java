package it.dstech.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.dstech.model.Ingrediente;

@Repository
public interface IngredienteRepository extends CrudRepository<Ingrediente, Long> {
	
//	List<Ingrediente> findById(Long id);


}
