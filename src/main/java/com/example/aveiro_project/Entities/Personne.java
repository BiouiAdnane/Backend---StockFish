package com.example.aveiro_project.Entities;

import com.example.aveiro_project.Enums.TypeEmploye;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int matriculation;
    @Enumerated(EnumType.STRING)
    private TypeEmploye typeEmploye;
    private String prenom;
    private String nom;
    private String email;
    private String adresse;
    private int tel;
    @OneToMany(mappedBy = "personne", fetch = FetchType.LAZY)
    private List<Operation> operations;

}
