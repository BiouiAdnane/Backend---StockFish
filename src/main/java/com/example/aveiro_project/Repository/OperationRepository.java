package com.example.aveiro_project.Repository;

import com.example.aveiro_project.Entities.Article;
import com.example.aveiro_project.Entities.Depot;
import com.example.aveiro_project.Entities.Operation;
import com.example.aveiro_project.Enums.TypeOp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface OperationRepository extends JpaRepository<Operation,Integer > {
    List<Operation> findOperationsByPersonne_Matriculation(int userId);

    List<Operation> findOperationByDateOpertaionEquals(Date date);
    List<Operation> findOperationsByDateOpertaionIsBetween(Date dateDebut,Date dateFin);
    List<Operation> findByTypeOprAndDepot(TypeOp typeOp, Depot depot);
    List<Article> findByArticleAndDepot(Article article,Depot depot);
    @Query(value="SELECT o.article.code_Article,o.article.quantite_Article,o.nLot from Operation o WHERE o.depot.code_Depot=1 GROUP BY o.article.code_Article ")
    List<Object[]> finddipoemb();
}
