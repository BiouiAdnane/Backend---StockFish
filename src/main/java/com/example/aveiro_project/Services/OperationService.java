package com.example.aveiro_project.Services;

import com.example.aveiro_project.Entities.Operation;
import com.example.aveiro_project.Enums.TypeOp;
import com.example.aveiro_project.Exceptions.DepotMax;
import com.example.aveiro_project.Exceptions.QuantiteInsufficient;

import java.util.Date;
import java.util.List;

public interface OperationService {
    List<Operation> getOperation();

    Operation saveOperation(Operation operation) throws QuantiteInsufficient, DepotMax;

    Operation getOperationId(int operationId) ;

    Operation updateOperation(Operation operation) throws QuantiteInsufficient, DepotMax;

    void deleteOperation(int operationId);

    List<Operation> listerOpByUserId(int userId);
    List<Operation> listerOpDate(Date date);
    List<Operation> listerOpPeriode(Date dateDebut , Date dateFin);

    List<Operation> listerOpEmb(TypeOp typeOp);
    List<Operation> listerOpPrf(TypeOp typeOp);
    List<Object[]> articleDepotEmb();
}
