package com.drinkapp.drink.barapp;

import com.drinkapp.drink.drinkorders.DrinkOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BartenderRepository extends JpaRepository<DrinkOrder, Integer> {

    Bartender add(Bartender bartender);

    DrinkOrder findByDrinkId(int id);




}
