package com.example.aveiro_project.Services;

import com.example.aveiro_project.DTOS.EntreeDTO;
import com.example.aveiro_project.DTOS.OperationDTO;
import com.example.aveiro_project.Entities.Operation;
import com.example.aveiro_project.Enums.TypeOp;
import com.example.aveiro_project.Exceptions.BlockUsed;
import com.example.aveiro_project.Exceptions.DepotMax;
import com.example.aveiro_project.Exceptions.QuantiteInsufficient;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface OperationService {
    List<OperationDTO> getOperation();

    OperationDTO saveOperation(OperationDTO operationDTO) throws QuantiteInsufficient, BlockUsed;

    OperationDTO getOperationId(int operationId) ;

    OperationDTO updateOperation(OperationDTO operationDTO) throws QuantiteInsufficient, DepotMax, BlockUsed;

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

    List<Integer> getAvailableAllee(int code_Depot);

    List<Integer> getAvailableRangee(int code_Depot, int allee);

    List<Integer> getAvailableNiveau(int code_Depot, int allee, int rangee);

    List<OperationDTO> listerOpDepot(int id);

    EntreeDTO nombreDop(int code_Article, int qte);
}
