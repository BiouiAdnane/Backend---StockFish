package com.example.aveiro_project.Repository;

import com.example.aveiro_project.Entities.Article;
import com.example.aveiro_project.Entities.Block;
import com.example.aveiro_project.Entities.Depot;
import com.example.aveiro_project.Entities.Operation;
import com.example.aveiro_project.Enums.TypeOp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    List<Operation> findAllByAlleeAndRangeeAndNiveauAndDepotEquals (int allee, int rangee, int niveau,Depot depot);
    List<Operation> findOperationsByDepot(Depot depot) ;
    @Query("SELECT COUNT(op) FROM Operation op " +
            "WHERE op.depot.code_Depot = :code_Depot " +
            "AND DATE(op.dateOpertaion) = :dateOperation " +
            "AND op.typeOpr = :typeOp")
    int countOperationsByCodeDepotAndDateAndTypeOp(@Param("code_Depot") int code_Depot,
                                                   @Param("dateOperation") java.sql.Date dateOperation,
                                                   @Param("typeOp") TypeOp typeOp);


    @Query("SELECT DISTINCT DATE(o.dateOpertaion) FROM Operation o WHERE o.depot.code_Depot = :codeDepot AND MONTH(o.dateOpertaion) = :month AND YEAR(o.dateOpertaion) = :year")
    List<String> getDistinctDates(@Param("codeDepot") String codeDepot, @Param("month") int month, @Param("year") int year);



    @Query("SELECT DATE(o.dateOpertaion), SUM(o.quantite) FROM Operation o WHERE o.depot.code_Depot = :codeDepot AND o.typeOpr = 'E' AND MONTH(o.dateOpertaion) = :month AND YEAR(o.dateOpertaion) = :year GROUP BY DATE(o.dateOpertaion)")
    List<Object[]> getArticleEntrees(@Param("codeDepot") String codeDepot, @Param("month") int month, @Param("year") int year);

    @Query("SELECT DATE(o.dateOpertaion), SUM(o.quantite) FROM Operation o WHERE o.depot.code_Depot = :codeDepot AND o.typeOpr = 'S' AND MONTH(o.dateOpertaion) = :month AND YEAR(o.dateOpertaion) = :year GROUP BY DATE(o.dateOpertaion)")
    List<Object[]> getArticleSorties(@Param("codeDepot") String codeDepot, @Param("month") int month, @Param("year") int year);
}
