package com.example.aveiro_project.Services;

import com.example.aveiro_project.Entities.Personne;
import com.example.aveiro_project.Exceptions.PersonneNotFoundException;
import com.example.aveiro_project.Repository.PersonneRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j

public class PersonneServiceImpl implements PersonneService{
    private PersonneRepository personneRepository;

    @Override
    public List<Personne> getPersonne(){
        return personneRepository.findAll();
    }


    @Override
    public Personne savePersonne(Personne personne){
        personneRepository.save(personne);
        log.info("Saving New Person");
        return personne;
    }


    @Override
    public Personne getPersonneId(int personneId) throws PersonneNotFoundException {
        Personne personne = personneRepository.findById(personneId).orElse(null);
        if (personne==null)
            throw new PersonneNotFoundException("User Not Found");
        return personne;
    }


    @Override
    public Personne updatePersonne(Personne personne) {
        log.info("Updating Customer");
        Personne savedPersonne =personneRepository.save(personne);
        return savedPersonne;
    }

    @Override
    public void deletePersonne(int personneId){

        personneRepository.deleteById(personneId);
    }


}
