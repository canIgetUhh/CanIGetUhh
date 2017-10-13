package com.drinkapp.drink.drinkOrder;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface DrinkOrderRepository extends JpaRepository<DrinkOrder, Integer> {

    DrinkOrder getByOrderId(int orderId);

//    List<DrinkOrder> getDrinks(ArrayList<Drink> drinks);

//    ArrayList<Drink> createdrinkOrder();

}
