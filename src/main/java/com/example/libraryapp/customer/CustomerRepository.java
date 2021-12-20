package com.example.libraryapp.customer;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    int countById(int paramInt);

    Optional<Customer> findCustomerByEmail(String paramString);

    Optional<Customer> findCustomerById(int paramInt);
}
