package it.dstech.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.dstech.model.Dolce;

@Repository
public interface DolceRepository extends CrudRepository<Dolce, Long> {

}
