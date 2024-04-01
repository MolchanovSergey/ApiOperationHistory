package ru.netology.molchanov.ApiOperationHistory.repository;

import org.springframework.stereotype.Repository;
import ru.netology.molchanov.ApiOperationHistory.domain.Operation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StatementRepository {
    Map<Integer, List<Operation>> statements = new HashMap<>();

    public void add(Integer i, List<Operation> list){
        statements.put(i, list);
    }
    public Map<Integer, List<Operation>> getStatements(){
        return statements;
    }
    public void setStatements(Map<Integer, List<Operation>> statements){
        this.statements = statements;
    }

}
