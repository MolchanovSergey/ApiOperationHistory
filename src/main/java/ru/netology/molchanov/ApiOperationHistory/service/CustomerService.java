package ru.netology.molchanov.ApiOperationHistory.service;

import org.springframework.stereotype.Service;
import ru.netology.molchanov.ApiOperationHistory.domain.Customer;

import java.util.ArrayList;
import java.util.List;
@Service
public class CustomerService {

    List<Customer> customers;

    public CustomerService(){
        customers = new ArrayList<>();
        customers.add(new Customer( "Клиент 1"));
        customers.add(new Customer("Клиент 2"));
        customers.add(new Customer("Клиент 3"));
    }

    public List<Customer> getCustomers(){
        return customers;
    }

    public Customer getCustomer(int id){
            return customers.get(id);
    }

    public void createCustomer(Customer customer){
        customers.add(customer);
    }

}
