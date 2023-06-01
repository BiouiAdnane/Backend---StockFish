package com.example.aveiro_project.Entities;

import com.example.aveiro_project.Enums.SizeArticle;
import com.example.aveiro_project.Enums.TypeArticle;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Palette {
    @Id
    private int codePalette;
    private int quantiteMax;
    private SizeArticle sizeArticle;
}
