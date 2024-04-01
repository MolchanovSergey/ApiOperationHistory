package ru.netology.molchanov.ApiOperationHistory.service;

import org.springframework.stereotype.Service;
import ru.netology.molchanov.ApiOperationHistory.domain.Operation;
import ru.netology.molchanov.ApiOperationHistory.repository.StatementRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StatementService {
    private final StatementRepository statementRepository;

    public StatementService(StatementRepository statementRepository) {
        this.statementRepository = statementRepository;
    }
    public void addId(int customerId, Operation operation){
        Map<Integer, List<Operation>> statements = statementRepository.getStatements();
        if (statements.containsKey(customerId)) {
            List<Operation> list = statements.get(customerId);
            list.add(operation);
            statements.put(customerId, list);
            statementRepository.setStatements(statements);
        } else {
            List<Operation> list = new ArrayList<>();
            list.add(operation);
            statementRepository.add(customerId, list);
        }


    }
    public List<Operation> operationByCustomer(int customerId) {
        Map<Integer, List<Operation>> statements = statementRepository.getStatements();
        return statements.get(customerId);
    }
    public Map<Integer, List<Operation>> getAllStatement(){
        return statementRepository.getStatements();
    }
    public void setStatementRepository(Map<Integer, List<Operation>> listMap){
        statementRepository.setStatements(listMap);
    }

}




