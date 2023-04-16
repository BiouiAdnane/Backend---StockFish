package com.example.aveiro_project.Services;

import com.example.aveiro_project.Entities.Operation;
import com.example.aveiro_project.Entities.Personne;
import com.example.aveiro_project.Exceptions.PersonneNotFoundException;
import com.example.aveiro_project.Exceptions.QuantiteInsufficient;

import java.util.Date;
import java.util.List;

public interface OperationService {
    List<Operation> getOperation();

    Operation saveOperation(Operation operation) throws QuantiteInsufficient ;

    Operation getOperationId(int operationId) ;

    Operation updateOperation(Operation operation) throws QuantiteInsufficient;

    void deleteOperation(int operationId);

    List<Operation> listerOpByUserId(int userId);
    List<Operation> listerOpDate(Date date);
    List<Operation> listerOpPeriode(Date dateDebut , Date dateFin);

}
