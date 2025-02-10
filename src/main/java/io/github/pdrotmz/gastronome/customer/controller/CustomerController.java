package io.github.pdrotmz.gastronome.customer.controller;

import io.github.pdrotmz.gastronome.customer.model.Customer;
import io.github.pdrotmz.gastronome.customer.service.CustomerSerivceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("customer")
public class CustomerController {

    private final CustomerSerivceImpl customerSerivce;

    public CustomerController(CustomerSerivceImpl customerSerivce) {
        this.customerSerivce = customerSerivce;
    }

    @PostMapping("/register")
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
        Customer newCustomer = customerSerivce.createCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCustomer);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> findAllCustomer() {
        List<Customer> customers = customerSerivce.findAllCustomer();
        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }

    @GetMapping("/find-by/id/{id}")
    public ResponseEntity<Customer> findCustomerById(@PathVariable String id) {
        Optional<Customer> customerId = customerSerivce.findCustomerById(id);
        return customerId.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/find-by/email/{email}")
    public ResponseEntity<Customer> findCustomerByEmail(@PathVariable String email) {
        Optional<Customer> customerEmail = customerSerivce.findCustomerByEmail(email);
        return customerEmail.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // TODO: REVIEW THESE UPDATES ENDPOINTS, GOING 404

    @PutMapping("/update/id/{id}")
    public ResponseEntity<Customer> updateCustomerById(@PathVariable String id, @Valid @RequestBody Customer customer) {
        Optional<Customer> updatedCustomer = customerSerivce.updateCustomerById(id, customer);
        return updatedCustomer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update/email/{email}")
    public ResponseEntity<Customer> updateCustomerByEmail(@PathVariable String email, @Valid @RequestBody Customer customer) {
        Optional<Customer> updatedCustomer = customerSerivce.updateCustomerByEmail(email, customer);
        return updatedCustomer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable String id) {
        customerSerivce.deleteCustomerById(id);
        return ResponseEntity.noContent().build();
    }
}
