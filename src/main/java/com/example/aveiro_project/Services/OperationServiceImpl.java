package com.example.aveiro_project.Services;

import com.example.aveiro_project.Entities.Article;
import com.example.aveiro_project.Entities.Depot;
import com.example.aveiro_project.Entities.Operation;
import com.example.aveiro_project.Enums.TypeOp;
import com.example.aveiro_project.Exceptions.DepotMax;
import com.example.aveiro_project.Exceptions.QuantiteInsufficient;
import com.example.aveiro_project.Repository.ArticleRepository;
import com.example.aveiro_project.Repository.DepotRepository;
import com.example.aveiro_project.Repository.OperationRepository;
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

@Service
@Transactional
@AllArgsConstructor
@Slf4j
@PersistenceContext

public class OperationServiceImpl implements OperationService{

    private DepotRepository depotRepository;
    private OperationRepository operationRepository;
    private ArticleRepository articleRepository;
    private EntityManager entityManager;
    @Override
    public List<Operation> getOperation() {
        return operationRepository.findAll();
    }

    @Override
    public Operation saveOperation(Operation operation) throws QuantiteInsufficient, DepotMax {
        log.info("Saving New Operation");
        Optional<Article> opArticle = articleRepository.findById(operation.getArticle().getCode_Article());
        Optional<Depot> opDepo = depotRepository.findById(operation.getDepot().getCode_Depot());
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
        return operation;
    }

    @Override
    public Operation getOperationId(int operationId) {
        return operationRepository.findById(operationId).orElse(null);
    }

    @Override
    public Operation updateOperation(Operation operation) throws QuantiteInsufficient, DepotMax {
        // baqi khassha tqad
        operationRepository.deleteById(operation.getIdOperation());
        return saveOperation(operation);
    }

    @Override
    public void deleteOperation(int operationId) {
        Article article = getOperationId(operationId).getArticle();
        Operation operation=getOperationId(operationId);
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
    public List<Operation> listerOpByUserId(int userId) {

        return operationRepository.findOperationsByPersonne_Matriculation(userId);
    }

    @Override
    public List<Operation> listerOpDate(Date date) {
        return operationRepository.findOperationByDateOpertaionEquals(date) ;
    }

    @Override
    public List<Operation> listerOpPeriode(Date dateDebut, Date dateFin) {
        return operationRepository.findOperationsByDateOpertaionIsBetween(dateDebut,dateFin) ;
    }

    @Override
    public List<Operation> listerOpEmb(TypeOp typeOp) {
//        List<Operation> operations= operationRepository.findOperationsByTypeOprIs(typeOp);
        Depot depot = depotRepository.getReferenceById(1);
        List<Operation>operations=operationRepository.findByTypeOprAndDepot(typeOp,depot);
        return operations;
    }

    @Override
    public List<Operation> listerOpPrf(TypeOp typeOp) {
//        List<Operation> operations= operationRepository.findOperationsByTypeOprIs(typeOp);
        Depot depot = depotRepository.getReferenceById(2);
        List<Operation> operations= operationRepository.findByTypeOprAndDepot(typeOp,depot);
        return operations;
    }

    @Override
    public List<Object[]> articleDepotEmb() {
        log.info("all articles");
        return operationRepository.finddipoemb() ;
    }


    public static long getNumberOfDaysSinceStartOfYear(LocalDate date) {

        LocalDate startOfYear = LocalDate.of(date.getYear(), 1, 1);


        return date.toEpochDay() - startOfYear.toEpochDay()+1;
    }

}
