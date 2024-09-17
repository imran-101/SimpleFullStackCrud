package com.userimran.service;

import com.userimran.entity.Customer;
import com.userimran.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer getCustomerById(Integer id) {
        return customerRepository.findById(id).orElseThrow(()->new RuntimeException("Customer not found"));
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Integer id) {
        customerRepository.deleteById(id);
    }
}
