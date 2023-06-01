package com.example.aveiro_project.DTOS;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArticleDTO {
    private int code_Article;
    private String designation;
}
