package com.userimran.controller;

import com.userimran.entity.Customer;
import com.userimran.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/customer")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
//        Customer savedCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(customerService.createCustomer(customer), HttpStatus.CREATED);
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable int id) {
        return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Integer id,@RequestBody Customer customer) {
        Customer existingCustomer = customerService.getCustomerById(id);
        if(existingCustomer == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        existingCustomer.setName(customer.getName());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setPhone(customer.getPhone());
        return new ResponseEntity<>(customerService.updateCustomer(existingCustomer), HttpStatus.OK);
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Integer id) {
        Customer customer = customerService.getCustomerById(id);
        if(customer == null)
            return ResponseEntity.notFound().build();
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }
}
