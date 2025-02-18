package ru.netology.molchanov.ApiOperationHistory.repository;

import org.springframework.stereotype.Repository;
import ru.netology.molchanov.ApiOperationHistory.domain.Operation;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StorageOperationRepository {
    private List<Operation> list = new ArrayList<>();

    public void add(Operation operation_hist){
        list.add(operation_hist);
    }
    public List<Operation> getAll(){
        return list;
    }
    public void setList(List<Operation> list){
        this.list = list;
    }
}
