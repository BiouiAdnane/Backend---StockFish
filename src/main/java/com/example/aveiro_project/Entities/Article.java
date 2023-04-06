package com.example.aveiro_project.Entities;

import com.example.aveiro_project.Enums.Ingredient;
import com.example.aveiro_project.Enums.Marque;
import com.example.aveiro_project.Enums.Nature;
import com.example.aveiro_project.Enums.Qualite;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity

public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int code_Article;
    private String designiation;
    private int quantite_Article;
    @Enumerated(EnumType.STRING)
    private Ingredient ingredient;
    @Enumerated(EnumType.STRING)
    private Marque marque;
    @Enumerated(EnumType.STRING)
    private Nature nature;
    @Enumerated(EnumType.STRING)
    private Qualite qualite;
@OneToMany(mappedBy = "article",fetch = FetchType.LAZY)
@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
private List<Operation>  operations;
//    @ManyToOne
//    private Depot depot;

}