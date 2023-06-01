package com.example.aveiro_project.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.DiscriminatorValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("ingredient")
public class Ingredient extends Famille {
    @OneToMany(mappedBy = "ingredient")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    List<Article> articles;

    public static Ingredient fromString(String value) {
        Ingredient ingredient = new Ingredient();
        ingredient.setId_Famille(Integer.parseInt(value));
        return ingredient;
    }
}
