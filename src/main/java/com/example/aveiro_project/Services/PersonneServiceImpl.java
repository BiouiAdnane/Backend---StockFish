package com.example.aveiro_project.Services;

import com.example.aveiro_project.Entities.Personne;
import com.example.aveiro_project.Exceptions.ArticleNotFoundException;
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
        Personne personne = personneRepository.findById(personneId).orElseThrow( ()->new PersonneNotFoundException("user not found") );
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

    @Override
    public List<Personne> searchPersonne(String keyword) {
        List<Personne> personnes = personneRepository.searchPersonne(keyword) ;
        return personnes;
    }
    @Override
    public int countPersonne() {
        return personneRepository.countAllByMatriculationIsNotNull();
    }

}
