package ru.netology.molchanov.ApiOperationHistory.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.molchanov.ApiOperationHistory.domain.Customer;
import ru.netology.molchanov.ApiOperationHistory.domain.Operation;
import ru.netology.molchanov.ApiOperationHistory.service.AsyncInputOperationService;
import ru.netology.molchanov.ApiOperationHistory.service.CustomerService;
import ru.netology.molchanov.ApiOperationHistory.service.StatementService;

import java.beans.Statement;

@RestController
@RequestMapping(path = "api/operation")
public class OperationController {

    private final AsyncInputOperationService service;

    public OperationController(AsyncInputOperationService service){
        this.service = service;
    }

    @PostMapping
    public void createCustomer(@RequestBody Operation operation){
        service.addOperation(operation);
    }
}
