package ru.netology.molchanov.ApiOperationHistory.controller;

import org.springframework.web.bind.annotation.*;
import ru.netology.molchanov.ApiOperationHistory.domain.Customer;
import ru.netology.molchanov.ApiOperationHistory.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping(path = "api/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service){
        this.service = service;
    }

    @GetMapping
    public List<Customer> getCustomers(){
        return service.getCustomers();

    }


    @GetMapping("{id}")
    public Customer getCustomer(@PathVariable("id") int id){
        return service.getCustomer(id);
    }

    @PostMapping
    public void createCustomer(@RequestBody Customer customer){
        service.createCustomer(customer);
    }


}
