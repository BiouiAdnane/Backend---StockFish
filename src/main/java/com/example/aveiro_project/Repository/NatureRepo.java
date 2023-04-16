package com.example.aveiro_project.Repository;

import com.example.aveiro_project.Entities.Nature;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NatureRepo extends JpaRepository<Nature,Integer> {
}
