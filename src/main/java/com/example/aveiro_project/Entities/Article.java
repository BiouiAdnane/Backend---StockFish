package com.example.aveiro_project.Entities;
import com.example.aveiro_project.Enums.SizeArticle;
import com.example.aveiro_project.Enums.TypeArticle;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Builder
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int code_Article;
    private String designiation;
    private int quantite_Article;
    @Enumerated(EnumType.STRING)
    private TypeArticle typeArticle;
    @Enumerated(EnumType.STRING)
    private SizeArticle size;
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
/*
    @ManyToOne
    private Depot depot;
*/

}