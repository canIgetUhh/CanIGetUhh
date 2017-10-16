package com.drinkapp.drink.drinkOrder;

import com.drinkapp.drink.drinks.Drink;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DrinkOrderRepository extends JpaRepository<Drink, Integer> {

    DrinkOrder findById(int id);
}
