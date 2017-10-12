package com.drinkapp.drink.drinkOrder;

import com.drinkapp.drink.drinks.Drink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface DrinkOrderRepository extends JpaRepository<Drink, Integer> {

    DrinkOrder getById(int id);

    List<DrinkOrder> getAllDrinkOrders ();

    ArrayList<Drink> createDrinkOrder();

}
