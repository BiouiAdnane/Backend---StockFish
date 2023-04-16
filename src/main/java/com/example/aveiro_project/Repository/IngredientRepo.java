package com.example.aveiro_project.Repository;

import com.example.aveiro_project.Entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepo extends JpaRepository<Ingredient,Integer> {
}
