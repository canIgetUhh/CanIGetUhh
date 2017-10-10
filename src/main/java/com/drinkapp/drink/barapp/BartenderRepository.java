package com.drinkapp.drink.barapp;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BartenderRepository extends JpaRepository<Bartender, Integer> {

    Bartender add(Bartender bartender);

}
