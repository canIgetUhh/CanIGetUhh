package com.drinkapp.drink.drinks;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DrinkRepository {

        Drink getByName(String strDrink);

    }

