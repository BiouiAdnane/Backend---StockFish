package com.example.aveiro_project.Repository;

import com.example.aveiro_project.Entities.Depot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepotRepository extends JpaRepository<Depot,Integer > {
    @Query("select d from Depot d where d.nom_Depot like :kw ")
    List<Depot> searchDepot(@Param(value = "kw") String keyword);
}
