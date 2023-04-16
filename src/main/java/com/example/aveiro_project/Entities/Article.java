package com.example.aveiro_project.Entities;
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
    @ManyToOne
    private Ingredient ingredient;
    @ManyToOne
    private Marque marque;
    @ManyToOne
    private Nature nature;
    @ManyToOne
    private Qualite qualite;
@OneToMany(mappedBy = "article",fetch = FetchType.LAZY)
@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
private List<Operation>  operations;
//    @ManyToOne
//    private Depot depot;

}