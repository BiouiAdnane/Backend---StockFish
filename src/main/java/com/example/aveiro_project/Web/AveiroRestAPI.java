package com.example.aveiro_project.Web;

import com.example.aveiro_project.Entities.Personne;
import com.example.aveiro_project.Exceptions.PersonneNotFoundException;
import com.example.aveiro_project.Services.PersonneService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")

public class AveiroRestAPI {
    private PersonneService personneService;
    public AveiroRestAPI(PersonneService personneService){this.personneService=personneService;}

    @PostMapping("/personnes")
    public Personne savePersonne(@RequestBody Personne personne){
        return personneService.savePersonne(personne);
    }

    @GetMapping("/personnes")
    public List<Personne> getPersonne(){
        return (List<Personne>) personneService.getPersonne();
    }

    @GetMapping("/personnes/{personneId}")
    public Personne getPersonne(@PathVariable String personneId) throws PersonneNotFoundException {
        return personneService.getPersonneId(Integer.parseInt(personneId));
    }

    @PutMapping("/personnes/{personneId}")
    public  Personne updatePersone(@PathVariable int personneId, @RequestBody Personne personne){
        personne.setMatriculation(personneId);
        return personneService.updatePersonne(personne);
    }

    @DeleteMapping("/personnes/{personneId}")
    public void deletePersonne(@PathVariable int personneId){
        personneService.deletePersonne(personneId);
    }

}
