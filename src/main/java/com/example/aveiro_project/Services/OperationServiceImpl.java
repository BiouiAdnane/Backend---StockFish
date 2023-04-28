package com.example.aveiro_project.Services;

import com.example.aveiro_project.DTOS.OperationDTO;
import com.example.aveiro_project.Entities.Article;
import com.example.aveiro_project.Entities.Depot;
import com.example.aveiro_project.Entities.Operation;
import com.example.aveiro_project.Enums.TypeOp;
import com.example.aveiro_project.Exceptions.DepotMax;
import com.example.aveiro_project.Exceptions.QuantiteInsufficient;
import com.example.aveiro_project.Repository.ArticleRepository;
import com.example.aveiro_project.Repository.DepotRepository;
import com.example.aveiro_project.Repository.OperationRepository;
import com.example.aveiro_project.mappers.OperationMapperImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
@PersistenceContext

public class OperationServiceImpl implements OperationService{

    private DepotRepository depotRepository;
    private OperationRepository operationRepository;
    private ArticleRepository articleRepository;
    private OperationMapperImpl dto;
    @Override
    public List<OperationDTO> getOperation() {
        List<Operation>operations= operationRepository.findAll();
        return operations.stream().map(operation -> dto.fromOperation(operation)).toList();

    }

    @Override
    public OperationDTO saveOperation(OperationDTO operationDTO) throws QuantiteInsufficient, DepotMax {
        log.info("Saving New Operation");
        Operation operation=dto.fromOperationDTO(operationDTO);
        Optional <Article>opArticle = articleRepository.findById(operation.getArticle().getCode_Article()) ;
        Optional <Depot> opDepo = depotRepository.findById(operation.getDepot().getCode_Depot());
//        Article article = operation.getArticle();
        if(opArticle != null || opDepo != null){
            Article article = opArticle.get();
            Depot depot = opDepo.get();
            if(operation.getTypeOpr()==TypeOp.E){
                if (operation.getQuantite()+depot.getQuantiteActuelle()>depot.getQauntiteMax())
                    throw new DepotMax("quantite max depasse");
                article.setQuantite_Article(article.getQuantite_Article()+operation.getQuantite());
                depot.setQuantiteActuelle(depot.getQuantiteActuelle()+operation.getQuantite());
            } else if (operation.getTypeOpr()==TypeOp.S) {
                if(article.getQuantite_Article()<operation.getQuantite() )
                    throw new QuantiteInsufficient("quantite inscufisante");
                article.setQuantite_Article(article.getQuantite_Article()-operation.getQuantite());
                depot.setQuantiteActuelle(depot.getQuantiteActuelle()-operation.getQuantite());
            }

        depotRepository.save(depot);
        articleRepository.save(article);
        operationRepository.save(operation);
        }
        OperationDTO operationDTO1=dto.fromOperation(operation);
        return operationDTO1;
    }

    @Override
    public OperationDTO getOperationId(int operationId) {
        Operation operation=operationRepository.findById(operationId).orElse(null);
        OperationDTO operationDTO=dto.fromOperation(operation);
        return operationDTO;
    }

    @Override
    public OperationDTO updateOperation(OperationDTO operationDTO) throws QuantiteInsufficient, DepotMax {
        // baqi khassha tqad
        Operation operation=dto.fromOperationDTO(operationDTO);
        deleteOperation(operationDTO.getIdOperation());
        saveOperation(operationDTO);
        return saveOperation(operationDTO);
    }

    @Override
    public void deleteOperation(int operationId) {
        Operation operation=dto.fromOperationDTO(getOperationId(operationId));
        Article article = operation.getArticle();
        if (operation.getTypeOpr()==TypeOp.E)
            article.setQuantite_Article(article.getQuantite_Article()-operation.getQuantite());
        else if (operation.getTypeOpr()==TypeOp.S) {
            article.setQuantite_Article(article.getQuantite_Article()+operation.getQuantite());
        }
        articleRepository.save(article);
        operationRepository.deleteById(operationId);
        //khassha tjarab
    }

    @Override
    public List<OperationDTO> listerOpByUserId(int userId) {
        List<Operation> operations= operationRepository.findOperationsByPersonne_Matriculation(userId);
        return operations.stream().map(op->dto.fromOperation(op)).toList();
    }

    @Override
    public List<OperationDTO> listerOpDate(Date date) {
        List<Operation> operations= operationRepository.findOperationByDateOpertaionEquals(date) ;
        return operations.stream().map(op->dto.fromOperation(op)).toList();
    }

    @Override
    public List<OperationDTO> listerOpPeriode(Date dateDebut, Date dateFin) {
        List<Operation> operations=operationRepository.findOperationsByDateOpertaionIsBetween(dateDebut,dateFin) ;
        return operations.stream().map(op->dto.fromOperation(op)).toList();
    }

    @Override
    public List<OperationDTO> listerOpEmb(TypeOp typeOp) {
        Depot depot = depotRepository.getReferenceById(1);
        List<Operation>operations=operationRepository.findByTypeOprAndDepot(typeOp,depot);
        return operations.stream().map(op->dto.fromOperation(op)).toList();
    }

    @Override
    public List<OperationDTO> listerOpPrf(TypeOp typeOp) {
        Depot depot = depotRepository.getReferenceById(2);
        List<Operation> operations= operationRepository.findByTypeOprAndDepot(typeOp,depot);
        return operations.stream().map(op->dto.fromOperation(op)).toList() ;
    }

    @Override
    public List<Object[]> articleDepotEmb() {
        log.info("all articles");
        return operationRepository.finddipoemb() ;
    }

    @Override
    public List<Object[]> articleDepotPrf() {
        return operationRepository.finddipoprf();
    }


    public static long getNumberOfDaysSinceStartOfYear(LocalDate date) {

        LocalDate startOfYear = LocalDate.of(date.getYear(), 1, 1);


        return date.toEpochDay() - startOfYear.toEpochDay()+1;
    }

}
