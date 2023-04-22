package com.example.aveiro_project.Repository;

import com.example.aveiro_project.Entities.Ingredient;
import com.example.aveiro_project.Entities.Qualite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QualiteRepo extends JpaRepository<Qualite,Integer> {
    @Query("select q from Qualite q where q.nom like :kw")
    List<Qualite> searchQualite(@Param(value = "kw") String keyword);
}
