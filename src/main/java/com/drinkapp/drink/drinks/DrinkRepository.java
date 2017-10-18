package com.drinkapp.drink.drinks;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DrinkRepository extends JpaRepository<Drink, Integer> {

        Drink getByStrDrink(String strDrink);

        Drink getByIdDrink(int idDrink);

    }

