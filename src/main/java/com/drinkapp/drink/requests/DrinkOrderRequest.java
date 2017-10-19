package com.drinkapp.drink.requests;

import com.drinkapp.drink.drinkEntry.DrinkEntry;
import com.drinkapp.drink.drinkOrder.DrinkOrder;
import com.drinkapp.drink.drinks.Drink;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<DrinkEntry> getDrinkEntries() {
        return drinks.stream()
                .map((drink) -> new DrinkEntry(drink, 1))
                .collect(Collectors.toList());

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DrinkOrderRequest that = (DrinkOrderRequest) o;

        return drinks != null ? drinks.equals(that.drinks) : that.drinks == null;
    }

    @Override
    public int hashCode() {
        return drinks != null ? drinks.hashCode() : 0;
    }
}
