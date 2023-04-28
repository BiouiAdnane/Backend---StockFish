package com.example.aveiro_project.Services;

import com.example.aveiro_project.DTOS.OperationDTO;
import com.example.aveiro_project.Entities.Operation;
import com.example.aveiro_project.Enums.TypeOp;
import com.example.aveiro_project.Exceptions.DepotMax;
import com.example.aveiro_project.Exceptions.QuantiteInsufficient;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface OperationService {
    List<OperationDTO> getOperation();

    OperationDTO saveOperation(OperationDTO operationDTO) throws QuantiteInsufficient, DepotMax;

    OperationDTO getOperationId(int operationId) ;

    OperationDTO updateOperation(OperationDTO operationDTO) throws QuantiteInsufficient, DepotMax;

    void deleteOperation(int operationId);

    List<OperationDTO> listerOpByUserId(int userId);
    List<OperationDTO> listerOpDate(Date date);
    List<OperationDTO> listerOpPeriode(Date dateDebut , Date dateFin);

    List<OperationDTO> listerOpEmb(TypeOp typeOp);
    List<OperationDTO> listerOpPrf(TypeOp typeOp);
    List<Object[]> articleDepotEmb();

    List<Object[]> articleDepotPrf();
    public static long getNumberOfDaysSinceStartOfYear(LocalDate date) {

        LocalDate startOfYear = LocalDate.of(date.getYear(), 1, 1);


        return date.toEpochDay() - startOfYear.toEpochDay()+1;
    }
}
