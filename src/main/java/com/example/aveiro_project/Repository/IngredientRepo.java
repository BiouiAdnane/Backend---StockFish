package com.example.aveiro_project.Repository;

import com.example.aveiro_project.Entities.Ingredient;
import com.example.aveiro_project.Entities.Marque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IngredientRepo extends JpaRepository<Ingredient,Integer> {
//    Ingredient findIngredientByNomContains(String kw);
    @Query("select i from Ingredient i where i.nom like :kw")
    List<Ingredient> searchIngredient(@Param(value = "kw") String keyword);
}
