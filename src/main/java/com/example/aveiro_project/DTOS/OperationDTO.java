package com.example.aveiro_project.DTOS;
import com.example.aveiro_project.Enums.TypeOp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;
@Data @AllArgsConstructor @NoArgsConstructor
public class OperationDTO {
    private int idOperation;
    private TypeOp typeOpr;
    private int code_Article;
    private String designation;
    private int code_Depot;
    private int matriculation;
    private int quantite;
    private String n_Lot;
    private int allee;
    private int rangee;
    private int niveau;
    private Date dateOpertaion;


}
