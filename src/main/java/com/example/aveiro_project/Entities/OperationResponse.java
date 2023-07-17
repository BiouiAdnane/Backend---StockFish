package com.example.aveiro_project.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationResponse {
    private List<String> dates;
    private List<Integer> ArticleEntrees;
    private List<Integer> ArticleSorties;
}
