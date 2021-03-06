package com.drinkapp.drink.barapp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BartenderRepository extends JpaRepository<Bartender, Integer> {

    Optional<Bartender> getByUsername(String username);

}


