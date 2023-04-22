package com.example.aveiro_project.Services;

import com.example.aveiro_project.Entities.Ingredient;
import com.example.aveiro_project.Entities.Marque;
import com.example.aveiro_project.Repository.IngredientRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class IngredientServiceImpl implements IngredientService {
    private IngredientRepo ingredientRepo;
    @Override
    public List<Ingredient> listerIngredients() {
        return ingredientRepo.findAll() ;
    }

    @Override
    public Ingredient saveIngredient(Ingredient ingredient) {
        return ingredientRepo.save(ingredient) ;
    }

    @Override
    public Ingredient updateIngredient(Ingredient ingredient) {
        return ingredientRepo.save(ingredient);
    }

    @Override
    public void deleteIngredient(int id) {
        ingredientRepo.deleteById(id);
    }

    @Override
    public List<Ingredient> searchIngredient(String keyword) {
        List<Ingredient> ingredients = ingredientRepo.searchIngredient(keyword) ;
        return ingredients;
    }


/*
    @Override
    public Ingredient findByName(String kw) {
        return ingredientRepo.findIngredientByNomContains(kw);
    }
*/

}
