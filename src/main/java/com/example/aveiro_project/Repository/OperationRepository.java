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
    @Query(value="SELECT a.code_Article,a.designiation, o.n_Lot, SUM(CASE WHEN o.typeOpr = 'E' THEN o.quantite ELSE -o.quantite END) AS quantite_total " +
            "from Article a JOIN Operation o ON a.code_Article = o.article.code_Article WHERE o.depot.code_Depot =1 GROUP BY a.code_Article , o.n_Lot ORDER BY a.code_Article,o.n_Lot")

    List<Object[]> finddipoemb();
    @Query(value="SELECT a.code_Article,a.designiation ,o.n_Lot, SUM(CASE WHEN o.typeOpr = 'E' THEN o.quantite ELSE -o.quantite END) AS quantite_total " +
            "from Article a JOIN Operation o ON a.code_Article = o.article.code_Article WHERE o.depot.code_Depot =2 GROUP BY a.code_Article , o.n_Lot ORDER BY a.code_Article,o.n_Lot")

    List<Object[]> finddipoprf();

    List<Operation> findAllByAlleeAndRangeeAndNiveau (int allee, int rangee, int niveau);
}
