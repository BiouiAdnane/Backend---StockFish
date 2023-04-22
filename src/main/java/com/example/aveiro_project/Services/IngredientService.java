package com.example.aveiro_project.Services;

import com.example.aveiro_project.Entities.Ingredient;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface IngredientService {
    public List<Ingredient> listerIngredients();
    public Ingredient saveIngredient(Ingredient ingredient);
    public Ingredient updateIngredient(Ingredient ingredient);
//    public Ingredient findByName(String kw);
    public void deleteIngredient(int id);

    List<Ingredient> searchIngredient(String keyword);
}
