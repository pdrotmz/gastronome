package io.github.pdrotmz.gastronome.customer.repository;

import io.github.pdrotmz.gastronome.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    @Query(value = "SELECT * FROM tb_customer c WHERE c.email = ?1", nativeQuery = true)
    Optional<Customer> findCustomerByEmail(String email);

    @Query(value = "DELETE FROM tb_customer c WHERE c.id = ?1", nativeQuery = true)
    void deleteCustomerById(String id);
}
