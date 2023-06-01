package com.example.aveiro_project.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
@Builder
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Famille {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id_Famille ;
    String nom;
}
