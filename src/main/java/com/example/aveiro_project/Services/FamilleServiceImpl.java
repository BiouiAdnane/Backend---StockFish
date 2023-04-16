package com.example.aveiro_project.Services;

import com.example.aveiro_project.Entities.*;
import com.example.aveiro_project.Repository.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@Transactional @AllArgsConstructor
public class FamilleServiceImpl implements FamilleService {
    private FamilleRepo familleRepo;
    private QualiteRepo qualiteRepo;
    private IngredientRepo ingredientRepo;
    private MarqueRepo marqueRepo;
    private NatureRepo natureRepo;

    @Override
    public Famille saveFamille(Famille famille) {
        return familleRepo.save(famille) ;
    }

    @Override
    public List<Famille> listerFamilles() {
        return familleRepo.findAll() ;
    }

    @Override
    public Famille getFamille(int id) {
        return familleRepo.findById(id).orElse(null) ;
    }

    @Override
    public Famille updateFamille(Famille famille) {
        return familleRepo.save(famille);
    }

    @Override
    public void deleteFamille(int id) {
        familleRepo.deleteById(id);
    }

    @Override
    public List<Marque> listerMarques() {
        return  marqueRepo.findAll() ;
    }

    @Override
    public List<Nature> listerNatures() {
        return natureRepo.findAll() ;
    }

    @Override
    public List<Ingredient> listerIngredients() {
        return ingredientRepo.findAll() ;
    }
    @Override
    public List<Qualite> listerQualites() {
        return qualiteRepo.findAll() ;
    }
}
