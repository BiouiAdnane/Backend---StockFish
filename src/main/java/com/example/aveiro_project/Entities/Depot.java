package com.example.aveiro_project.Entities;

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
public class Depot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int code_Depot;
    private String nom_Depot;
    private int qauntiteMax;
    private int quantiteActuelle;
    private int nbrMaxAllee;
    private int nbrMaxRangee;
    private int nbrMaxNiveau;
    @OneToMany(mappedBy = "depot")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Operation> operations;
    @OneToMany(mappedBy = "depot")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Block> blocks;
}
