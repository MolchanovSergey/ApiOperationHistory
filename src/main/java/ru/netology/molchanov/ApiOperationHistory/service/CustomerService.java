package ru.netology.molchanov.ApiOperationHistory.service;

import org.springframework.stereotype.Service;
import ru.netology.molchanov.ApiOperationHistory.domain.Customer;
import ru.netology.molchanov.ApiOperationHistory.exception.OperationRuntimeException;
import ru.netology.molchanov.ApiOperationHistory.repository.StorageCustomerRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public class CustomerService {
    private final StorageCustomerRepository storageCustomerRepository;
    public CustomerService(StorageCustomerRepository storageCustomerRepository) {
        this.storageCustomerRepository = storageCustomerRepository;
    }
    private int customerId = 1;

    public int countId() {
        if (customerId == 1) {
            List<Customer> listCustomer = storageCustomerRepository.getAll();
            for (Customer cust : listCustomer) {
                if (cust != null) {
                    customerId++;
                }
            }
            return customerId;
        }
        return 0;
    }
    public void add(Customer customer) throws OperationRuntimeException {
        try {
            storageCustomerRepository.add(customer);
        } catch (OperationRuntimeException e) {
            System.out.println("Error add customer");
        }
    }
    public List<Customer> getAll() {
        return storageCustomerRepository.getAll();
    }
    public Customer getCustomer(int id) {
        List<Customer> listCustomer = storageCustomerRepository.getAll();
        for (Customer customer : listCustomer) {
            if (id == customer.getId()) {
                return customer;
            }
        }
        System.out.println("Customer not found");
        return null;
    }
    public Customer editCustomer(Customer customer, String name) {
        customer.setName(name);
        return customer;
    }
    public Customer deleteCustomer(int id) {
        List<Customer> listCustomer = storageCustomerRepository.getAll();
        for (Customer customer : listCustomer) {
            if (id == customer.getId()) {
                listCustomer.remove(customer);
                storageCustomerRepository.setList(listCustomer);
                return customer;
            }
        }
        System.out.println("Customer not found");
        return null;
    }
    @PostConstruct
    public void initStorageCustomer() {
        storageCustomerRepository.add(new Customer(1, "Spring", 25));
        storageCustomerRepository.add(new Customer(2, "Boot", 30));
    }

}
