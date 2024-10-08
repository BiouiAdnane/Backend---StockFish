package com.example.aveiro_project.Repository;

import com.example.aveiro_project.Entities.Famille;
import com.example.aveiro_project.Entities.Marque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FamilleRepo extends JpaRepository<Famille,Integer> {
    List<Famille> findFamilleByNomContaining(String kw);

    int countFamilleByNomNotNull();
}
