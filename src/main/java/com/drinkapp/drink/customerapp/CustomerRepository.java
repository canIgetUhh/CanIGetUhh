package com.drinkapp.drink.customerapp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Optional<Customer> getByUsername(String username);

    Customer findById(int id);
}
