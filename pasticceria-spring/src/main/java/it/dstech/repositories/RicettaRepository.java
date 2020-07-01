package it.dstech.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.dstech.model.Ricetta;

@Repository
public interface RicettaRepository extends CrudRepository<Ricetta, Long> {

}
