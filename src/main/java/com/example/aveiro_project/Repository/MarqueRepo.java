package com.example.aveiro_project.Repository;

import com.example.aveiro_project.Entities.Marque;
import com.example.aveiro_project.Entities.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MarqueRepo extends JpaRepository<Marque,Integer> {
    @Query("select m from Marque m where m.nom like :kw")
    List<Marque> searchMarque(@Param(value = "kw") String keyword);
}
