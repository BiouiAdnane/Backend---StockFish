package com.example.aveiro_project.Repository;

import com.example.aveiro_project.Entities.Ingredient;
import com.example.aveiro_project.Entities.Nature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface NatureRepo extends JpaRepository<Nature,Integer> {
    @Query("select n from Nature n where n.nom like :kw")
    List<Nature> searchNature(@Param(value = "kw") String keyword);
}
