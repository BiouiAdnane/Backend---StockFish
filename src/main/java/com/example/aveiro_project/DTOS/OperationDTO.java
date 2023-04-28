package com.example.aveiro_project.DTOS;
import com.example.aveiro_project.Enums.TypeOp;
import lombok.Data;
import java.util.Date;
@Data
public class OperationDTO {
    private int idOperation;
    private TypeOp typeOpr;
    private int codeArticle;
    private String designation;
    private int CodeDepot;
    private int matriculation;
    private int quantite;
    private String nLot;
    private int allee;
    private int rangee;
    private int niveau;
    private Date dateOpertaion;
}
