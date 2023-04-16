package com.example.aveiro_project.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
@DiscriminatorValue("marque")
public class Marque extends Famille {
    @OneToMany(mappedBy = "marque")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    List<Article> articles;
}
