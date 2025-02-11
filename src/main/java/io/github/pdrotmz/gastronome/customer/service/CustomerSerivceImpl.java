package io.github.pdrotmz.gastronome.customer.service;

import io.github.pdrotmz.gastronome.customer.model.Customer;
import io.github.pdrotmz.gastronome.customer.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerSerivceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerSerivceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> findAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findCustomerById(String id) {
        if(customerRepository.findById(id) == null ||
                customerRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Customer does not exist");
        }
        return customerRepository.findById(id);
    }

    @Override
    public Optional<Customer> findCustomerByEmail(String email) {
        if(customerRepository.findCustomerByEmail(email) == null ||
                customerRepository.findCustomerByEmail(email).isEmpty()) {
            throw new EntityNotFoundException("Customer does not exist");
        }
        return customerRepository.findCustomerByEmail(email);
    }

    @Override
    public Optional<Customer> updateCustomerById(String id, Customer customer) {
        return customerRepository.findById(id).map(existingCustomer -> {
            existingCustomer.setName(customer.getName());
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setPhone(customer.getPhone());

            return customerRepository.save(existingCustomer);
        })
                .map(Optional::of)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public Optional<Customer> updateCustomerByEmail(String email, Customer customer) {
        return customerRepository.findCustomerByEmail(email).map(existingCustomer -> {
            existingCustomer.setName(customer.getName());
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setPhone(customer.getPhone());

            return customerRepository.save(existingCustomer);
        })
                .map(Optional::of)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void deleteCustomerById(String id) {
        customerRepository.deleteCustomerById(id);
    }
}
