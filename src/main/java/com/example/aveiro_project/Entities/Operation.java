package com.example.aveiro_project.Entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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
    @ManyToOne
    private Article article;
    private int quantite;
    private  final String nLot= "AT"+ getNumberOfDaysSinceStartOfYear(LocalDate.now())+"C";
    private int allee;
    private int rangee;
    private int niveau;
    private Date dateOpertaion=new Date();

    public static long getNumberOfDaysSinceStartOfYear(LocalDate date) {

        LocalDate startOfYear = LocalDate.of(date.getYear(), 1, 1);


        return date.toEpochDay() - startOfYear.toEpochDay()+1;
    }
    
}
