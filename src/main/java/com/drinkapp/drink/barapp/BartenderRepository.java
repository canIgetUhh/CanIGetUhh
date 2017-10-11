package com.drinkapp.drink.barapp;

import com.drinkapp.drink.drinkorders.DrinkOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BartenderRepository extends JpaRepository<Bartender, Integer> {

    Bartender add(Bartender bartender);

    Bartender findById(int id);

}
