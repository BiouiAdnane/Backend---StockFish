package com.example.aveiro_project.Web;

import com.example.aveiro_project.Entities.Personne;
import com.example.aveiro_project.Exceptions.PersonneNotFoundException;
import com.example.aveiro_project.Services.PersonneService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")

public class PersonneRestAPI {
    private PersonneService personneService;
    public PersonneRestAPI(PersonneService personneService){this.personneService=personneService;}

    @PostMapping("/personnes")
    public Personne savePersonne(@RequestBody Personne personne){

        return personneService.savePersonne(personne);
    }

    @GetMapping("/personnes")
    public List<Personne> getPersonne(){

        return  personneService.getPersonne();
    }

    @GetMapping("/personnes/{personneId}")
    public Personne getPersonne(@PathVariable int personneId) throws PersonneNotFoundException {
        return personneService.getPersonneId(personneId);
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


    @GetMapping("/personnes/search")
    public List<Personne> searchUtilisateur(@RequestParam(name = "keyword" , defaultValue = "") String keyword){
        return personneService.searchPersonne("%"+keyword+"%");
    }
    @GetMapping("/personnes/count")
    public int countPersonne(){
        return  personneService.countPersonne();
    }
}
