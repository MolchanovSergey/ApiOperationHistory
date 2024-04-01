package ru.netology.molchanov.ApiOperationHistory.controller;

import org.springframework.web.bind.annotation.*;
import ru.netology.molchanov.ApiOperationHistory.domain.Customer;
import ru.netology.molchanov.ApiOperationHistory.domain.Operation;
import ru.netology.molchanov.ApiOperationHistory.service.AsyncInputOperationService;
import ru.netology.molchanov.ApiOperationHistory.service.CustomerService;
import ru.netology.molchanov.ApiOperationHistory.service.OperationService;
import ru.netology.molchanov.ApiOperationHistory.service.StatementService;

import java.beans.Statement;
import java.util.List;
import java.util.concurrent.Callable;

@RestController
@RequestMapping(path = "api/operations")
public class OperationController {
    private final OperationService operationService;
    private final CustomerService customerService;
    private final AsyncInputOperationService asyncInputOperationService;
    private final StatementService statementService;


    public OperationController(OperationService operationService, CustomerService customerService, AsyncInputOperationService asyncInputOperationService, StatementService statementService) {
        this.operationService = operationService;
        this.customerService = customerService;
        this.asyncInputOperationService = asyncInputOperationService;
        this.statementService = statementService;

    }

    @GetMapping
    public List<Operation> getAllOperation() {
        return operationService.getAll();
    }

    @GetMapping("/{id}")
    public List<Operation> getOperationByCustomerId(@PathVariable int id) {
        return operationService.getOperationsCustomer(id);
    }

    @PostMapping("/post/{id}/{amount}/{date}")
    public String addOperation(@PathVariable int id, @PathVariable double amount, @PathVariable int date) {
        Customer customer = customerService.getCustomer(id);
        if (customer.getId() == id) {
            int operationId = operationService.countId();
            Operation operation = new Operation(operationId, amount, date);
            statementService.addId(id, operation);
            asyncInputOperationService.offerOperation(operation);
            return operation + " added";
        }
        return "Customer id not found";
    }

    @DeleteMapping("/delete/{customerId}/{operationId}")
    public String deleteOperation(@PathVariable int customerId, @PathVariable int operationId){
        Operation operation = operationService.deleteOperation(customerId, operationId);
        return operation + " is delete";
    }
}




