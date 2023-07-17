package com.example.aveiro_project.Services;

import com.example.aveiro_project.Entities.Personne;
import com.example.aveiro_project.Exceptions.PersonneNotFoundException;

import java.util.List;

public interface PersonneService {
    List<Personne> getPersonne();

    Personne savePersonne(Personne personne);

    Personne getPersonneId(int personneId) throws PersonneNotFoundException;

    Personne updatePersonne(Personne personne);

    void deletePersonne(int personneId);
    List<Personne> searchPersonne(String keyword);
    int countPersonne();
}
