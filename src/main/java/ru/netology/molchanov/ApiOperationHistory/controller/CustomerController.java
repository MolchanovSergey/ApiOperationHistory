package ru.netology.molchanov.ApiOperationHistory.controller;

import org.springframework.web.bind.annotation.*;
import ru.netology.molchanov.ApiOperationHistory.domain.Customer;
import ru.netology.molchanov.ApiOperationHistory.DTO.CustomerDTO;
import ru.netology.molchanov.ApiOperationHistory.response.CustomersGetResponse;
import ru.netology.molchanov.ApiOperationHistory.service.CustomerService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping
    public CustomersGetResponse getCustomers() {
        List<Customer> customers = customerService.getAll();
        List<CustomerDTO> customerDTOS = customers.stream()
                .map(customer -> new CustomerDTO(customer.getId(), customer.getName()))
                .collect(Collectors.toList());
        return new CustomersGetResponse(customerDTOS);
    }

    @GetMapping("/{id}")
    public CustomerDTO getCustomer(@PathVariable int id){
        Customer customer = customerService.getCustomer(id);
        return new CustomerDTO(customer.getId(), customer.getName());
    }
    @PostMapping("/post/{name}/{age}")
    public String addCustomer(@PathVariable String name, @PathVariable int age) {
        int customerId = customerService.countId();
        Customer customer = new Customer(customerId, name, age);
        customerService.add(customer);
        return customer + " added";
    }
    @PutMapping("/put/{id}/{name}")
    public String editCustomer(@PathVariable int id, @PathVariable String name) {
        Customer customer = customerService.getCustomer(id);
        Customer customerEdit = customerService.editCustomer(customerService.getCustomer(id), name);
        return customer + " rename to " + customerEdit;
    }
    @DeleteMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable int id) {
        return customerService.deleteCustomer(id) + " is delete";

    }
}
