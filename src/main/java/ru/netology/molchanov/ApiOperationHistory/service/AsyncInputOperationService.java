package ru.netology.molchanov.ApiOperationHistory.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.netology.molchanov.ApiOperationHistory.domain.Operation;

import java.util.LinkedList;
import java.util.Queue;

@Service
public class AsyncInputOperationService {

    private final Queue<Operation> operations = new LinkedList<>();

    @Autowired
    private StatementService statementService;

    @PostConstruct
    public void init() {
        this.startProcessing();
    }

    public void startProcessing() {
        Thread t = new Thread(this::processQueue);
        t.start();
    }

    private void processQueue() {
        while (true) {
            Operation operation = operations.poll();
            if (operation == null) {
                try {
                    System.out.println("No operations");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            } else {
                System.out.println("Processing operation " + operation);
                processOperation(operation);
            }
        }
    }

    private void processOperation(Operation operation) {
        statementService.saveOperation(operation);
    }

    public boolean addOperation(Operation operation) {
        System.out.println("Operation added for processing " + operation);
        return operations.offer(operation);
    }
}