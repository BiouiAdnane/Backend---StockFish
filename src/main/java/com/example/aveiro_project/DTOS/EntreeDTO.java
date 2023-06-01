package com.example.aveiro_project.DTOS;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data @AllArgsConstructor @NoArgsConstructor
@Builder
public class EntreeDTO {
    private int nombre;
    private List<Integer> quantites;
}
