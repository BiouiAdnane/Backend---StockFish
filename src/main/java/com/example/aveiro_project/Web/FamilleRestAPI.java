package com.example.aveiro_project.Web;

import com.example.aveiro_project.Entities.*;
import com.example.aveiro_project.Services.FamilleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class FamilleRestAPI {
    private FamilleService familleService;
    @GetMapping("/familles")
    public List<Famille> listFamilles(){
        return familleService.listerFamilles();
    }
    @GetMapping("/familles/marques")
    public List<Marque> listMarques(){
        return familleService.listerMarques();
    }
    @GetMapping("/familles/natures")
    public List<Nature> listNatures(){
        return familleService.listerNatures();
    }
    @GetMapping("/familles/ingredients")
    public List<Ingredient> listIngredients(){
        return familleService.listerIngredients();}
    @GetMapping("/familles/qualites")
    public List<Qualite> listQualites(){
        return familleService.listerQualites();}
}
