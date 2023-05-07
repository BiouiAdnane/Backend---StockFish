package com.example.aveiro_project.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Block {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_Block;
    private int allee;
    private int rangee;
    private int niveau;
    @ManyToOne
    private Depot depot;
    private int quantite;
}