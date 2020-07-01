package it.dstech.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.dstech.model.Ordinazione;


@Repository
public interface OrdinazioneRepository extends CrudRepository<Ordinazione, Long> {


}
