package com.example.aveiro_project.Web;

import com.example.aveiro_project.Entities.*;
import com.example.aveiro_project.Exceptions.DepotNotFoundException;
import com.example.aveiro_project.Services.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class FamilleRestAPI {
    private FamilleService familleService;
    private MarqueService marqueService;
    private NatureService natureService;
    private IngredientService ingredientService;
    private QualiteService qualiteService;

    //FAMILLES

    @GetMapping("/familles")
    public List<Famille> listFamilles(){
        return familleService.listerFamilles();
    }
    @GetMapping("/familles/nom/{kw}")//gha 7iyedha
    public List<Famille> findFamilleByNom(@PathVariable String kw){
        return familleService.findByNom(kw);
    }
    @GetMapping("/familles/id/{id}")
    public Famille findFamilleById(@PathVariable int id){
        return familleService.getFamille(id);
    }
    @GetMapping("/familles/count")
    public int countFamilles(){
        return familleService.countFamille();
    }



    //~~~~~~~~~~~~~INGREDIENTS`````````````````\\

    @GetMapping("/familles/ingredients")
    public List<Ingredient> listIngredients(){return ingredientService.listerIngredients();}
    @PostMapping("/familles/ingredients")
    public Ingredient saveIngredient(@RequestBody Ingredient ingredient){return ingredientService.saveIngredient(ingredient);}
    @PutMapping("/familles/ingredients")
    public Ingredient updateIngredient(@RequestBody Ingredient ingredient){return ingredientService.updateIngredient(ingredient);}
    @DeleteMapping("/familles/ingredients/{id}")
    public void deleteIngredient(@PathVariable int id){
         ingredientService.deleteIngredient(id);
    }
    @GetMapping("/familles/ingredients/search")
    public List<Ingredient> searchIngredient(@RequestParam(name = "keyword" , defaultValue = "") String keyword){
        return ingredientService.searchIngredient("%"+keyword+"%");
    }




    //~~~~~~~~~~~~~MARQUE`````````````````\\

    @GetMapping("/familles/marques")
    public List<Marque> listMarques(){
        return marqueService.listerMarques();
    }
    @PostMapping("/familles/marques")
    public Marque saveMarque(@RequestBody Marque marque){
        return marqueService.saveMarque(marque);
    }
    @PutMapping("/familles/marques")
    public Marque updateMarque(@RequestBody Marque marque){
        return marqueService.updateMarque(marque);
    }
    @DeleteMapping("/familles/marques/{id}")
    public void deleteMarque(@PathVariable int id){
        marqueService.deleteMarque(id);
    }
    @GetMapping("/familles/marques/search")
    public List<Marque> searchMarque(@RequestParam(name = "keyword" , defaultValue = "") String keyword){
        return marqueService.searchMarque("%"+keyword+"%");
    }






    //~~~~~~~~~~~~~NATURE`````````````````\\

    @GetMapping("/familles/natures")
    public List<Nature> listNatures(){
        return natureService.listerNatures();
    }
    @PostMapping("/familles/natures")
    public Nature saveNature(@RequestBody Nature nature){
        return natureService.saveNature(nature);
    }
    @PutMapping("/familles/natures")
    public Nature updateNature(@RequestBody Nature nature){
        return natureService.updateNature(nature) ;
    }
    @DeleteMapping("/familles/natures/{id}")
    public void deleteNature(@PathVariable int id){
        natureService.deleteNature(id);
    }
    @GetMapping("/familles/natures/search")
    public List<Nature> searchNature(@RequestParam(name = "keyword" , defaultValue = "") String keyword){
        return natureService.searchNature("%"+keyword+"%");
    }




    //~~~~~~~~~~~~~QUALITE`````````````````\\
    @GetMapping("/familles/qualites")
    public List<Qualite> listQualites(){
        return qualiteService.listerQualites();}
    @PostMapping("/familles/qualites")
    public Qualite saveQualite(@RequestBody Qualite qualite){
        return qualiteService.saveQualite(qualite);
    }
    @PutMapping("/familles/qualites")
    public Qualite updateQualite(@RequestBody Qualite qualite){
        return qualiteService.updateQualite(qualite) ;
    }
    @DeleteMapping("/familles/qualites/{id}")
    public void deleteQualite(@PathVariable int id){
        qualiteService.deleteQualite(id);
    }
    @GetMapping("/familles/qualites/search")
    public List<Qualite> searchQualite(@RequestParam(name = "keyword" , defaultValue = "") String keyword){
        return qualiteService.searchQualite("%"+keyword+"%");
    }

}
