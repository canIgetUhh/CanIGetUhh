package com.drinkapp.drink.customerapp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
//    creates new customer
    Customer add(Customer customer);

//    shows list of customers
    List<Customer> get ();

//    gets Customer by Id
    Customer getById(int id);

}
