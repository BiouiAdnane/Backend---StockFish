package com.example.aveiro_project.mappers;

import com.example.aveiro_project.DTOS.OperationDTO;
import com.example.aveiro_project.Entities.Article;
import com.example.aveiro_project.Entities.Depot;
import com.example.aveiro_project.Entities.Operation;
import com.example.aveiro_project.Entities.Personne;
import com.example.aveiro_project.Repository.ArticleRepository;
import com.example.aveiro_project.Repository.DepotRepository;
import com.example.aveiro_project.Repository.PersonneRepository;
import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


@Service @AllArgsConstructor
public class OperationMapperImpl {
    private PersonneRepository personneRepository;
    private ArticleRepository articleRepository;
    private DepotRepository depotRepository;
    public OperationDTO fromOperation(Operation operation){
        OperationDTO operationDTO = new OperationDTO();
        BeanUtils.copyProperties(operation,operationDTO);
        operationDTO.setMatriculation(operation.getPersonne().getMatriculation());
        operationDTO.setCode_Article(operation.getArticle().getCode_Article());
        operationDTO.setDesignation(operation.getArticle().getDesigniation());
        operationDTO.setCode_Depot(operation.getDepot().getCode_Depot());
        return operationDTO;
    }
    public Operation fromOperationDTO(OperationDTO operationDTO){
        Operation operation=new Operation();
        BeanUtils.copyProperties(operationDTO,operation);
        Personne personne= personneRepository.findById(operationDTO.getMatriculation()).orElse(null) ;
        operation.setPersonne(personne);
        Article article = articleRepository.findById(operationDTO.getCode_Article()).orElse(null);
        operation.setArticle(article);
        Depot depot=depotRepository.findById(operationDTO.getCode_Depot()).orElse(null);
        operation.setDepot(depot);
        return operation;
    }
}
