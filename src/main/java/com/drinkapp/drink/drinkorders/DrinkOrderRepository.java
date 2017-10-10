package com.drinkapp.drink.drinkorders;

import java.util.List;

public interface DrinkOrderRepository {
//    add a drinkorder
    DrinkOrder add(DrinkOrder drinkOrder);

//    update the drinkorder
    DrinkOrder update(DrinkOrder drinkOrder);

//    get the drinkorder by id
    DrinkOrder getById(int id);

//    get list of all drinkorders
    List<DrinkOrder> get();

//    delete drinkorder
    DrinkOrder delete(int id);

}
