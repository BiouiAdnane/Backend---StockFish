package com.example.aveiro_project.Entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOperation;
    private String typeOpr;
    private int quantite;
    private String nLot;
    private int allee;
    private int rangee;
    private int niveau;
    private Date dateOpertaion;


}
