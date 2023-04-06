package com.example.aveiro_project.Repository;

import com.example.aveiro_project.Entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface OperationRepository extends JpaRepository<Operation,Integer > {
    List<Operation> findOperationsByPersonne_Matriculation(int userId);

    List<Operation> findOperationByDateOpertaionEquals(Date date);
}
