package com.example.aveiro_project.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class DispoArticle {
    private int code_Article;
    private String designation;
    private int n_Lot;
    private int quantite_total;
}
