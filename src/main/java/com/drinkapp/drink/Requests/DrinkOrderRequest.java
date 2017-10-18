package com.drinkapp.drink.Requests;

import com.drinkapp.drink.drinks.Drink;

import java.util.ArrayList;
import java.util.List;

public class DrinkOrderRequest {

    private ArrayList<Drink> drinks;

    public DrinkOrderRequest() {
    }

    public ArrayList<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(ArrayList<Drink> drinks) {
        this.drinks = drinks;
    }
}
