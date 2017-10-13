package com.drinkapp.drink.drinks;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AllOfTheDrinks {

    private List<Drink> drinks;

    @Override
    public String toString() {
        String drinksList = "";
        for (Drink drink : drinks) {
            drinksList += drink.getStrDrink() + ": " + drink.getIdDrink();
        }
        return "AllOfTheDrinks{" +
                "drinks=" + drinksList +
                '}';
    }

    public AllOfTheDrinks() {
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }
}
