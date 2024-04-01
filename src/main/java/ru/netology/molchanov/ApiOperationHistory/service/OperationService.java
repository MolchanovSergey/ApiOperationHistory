package ru.netology.molchanov.ApiOperationHistory.service;

import org.springframework.stereotype.Service;
import ru.netology.molchanov.ApiOperationHistory.domain.Operation;
import ru.netology.molchanov.ApiOperationHistory.exception.OperationRuntimeException;
import ru.netology.molchanov.ApiOperationHistory.repository.StorageOperationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class OperationService {
    private final StorageOperationRepository operationStorageRepository;
    private final StatementService statementService;


    public OperationService(StorageOperationRepository operationStorageRepository, StatementService statementService) {
        this.operationStorageRepository = operationStorageRepository;
        this.statementService = statementService;

    }

    private int operationId = 1;

    public int countId() {
        if (operationId == 1) {
            List<Operation> listOperations = operationStorageRepository.getAll();
            for (Operation oper : listOperations) {
                if (oper != null) {
                    operationId++;
                }
            }
            return operationId;
        }
        return 0;
    }

    public void addOperation(Operation operation) throws OperationRuntimeException {
        try {
            operationStorageRepository.add(operation);
        } catch (OperationRuntimeException e) {
            System.out.println("Ошибка добавления получателя");
        }
    }

    public List<Operation> getOperationsCustomer(int clientId) {
        return statementService.operationByCustomer(clientId);
    }

    public List<Operation> getAll() {
        return operationStorageRepository.getAll();
    }

    public Operation deleteOperation(int customerId, int operationId) {
        Map<Integer, List<Operation>> integerListMap = statementService.getAllStatement();
        List<Operation> listOperations = integerListMap.get(customerId);
        if (integerListMap.get(customerId) != null) {
            for (Operation operation : listOperations) {
                if (operationId == operation.getId()) {
                    listOperations.remove(operation);
                    operationStorageRepository.setList(listOperations);
                    integerListMap.put(customerId, listOperations);
                    statementService.setStatementRepository(integerListMap);
                    return operation;
                }
            }

        }
        return null;
    }
}

