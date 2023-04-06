package com.example.aveiro_project.Services;

import com.example.aveiro_project.Entities.Operation;
import com.example.aveiro_project.Entities.Personne;
import com.example.aveiro_project.Exceptions.PersonneNotFoundException;

import java.util.List;

public interface OperationService {
    List<Operation> getOperation();

    Operation saveOperation(Operation operation);

    Operation getOperationId(int operationId) ;

    Operation updateOperation(Operation operation);

    void deleteOperation(int operationId);

    List<Operation> listerOpByUserId(int userId);

}
