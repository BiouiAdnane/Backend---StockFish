package com.example.aveiro_project.Repository;

import com.example.aveiro_project.Entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation,Integer > {
}
