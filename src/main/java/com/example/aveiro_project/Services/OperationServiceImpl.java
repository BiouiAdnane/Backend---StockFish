package com.example.aveiro_project.Services;

import com.example.aveiro_project.Entities.Operation;
import com.example.aveiro_project.Repository.OperationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
@AllArgsConstructor
@Slf4j

public class OperationServiceImpl implements OperationService{
    private OperationRepository operationRepository;
    @Override
    public List<Operation> getOperation() {
        return operationRepository.findAll();
    }

    @Override
    public Operation saveOperation(Operation operation) {
        operationRepository.save(operation);
        log.info("Saving New Operation");
        return operation;
    }

    @Override
    public Operation getOperationId(int operationId) {
        Operation operation=operationRepository.findById(operationId).orElse(null);
        return operation;
    }

    @Override
    public Operation updateOperation(Operation operation) {
        Operation updatedOperation=operationRepository.save(operation);
        return updatedOperation;
    }

    @Override
    public void deleteOperation(int operationId) {
        operationRepository.deleteById(operationId);
    }
}
