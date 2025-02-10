package io.github.pdrotmz.gastronome.customer.service;

import io.github.pdrotmz.gastronome.customer.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    List<Customer> findAllCustomer();
    Optional<Customer> findCustomerById(String id);
    Optional<Customer> findCustomerByEmail(String email);
    Optional<Customer> updateCustomerById(String id, Customer customer);
    Optional<Customer> updateCustomerByEmail(String email, Customer customer);
    void deleteCustomerById(String id);
}
