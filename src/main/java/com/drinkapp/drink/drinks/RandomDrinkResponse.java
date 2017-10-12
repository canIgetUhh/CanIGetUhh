package com.drinkapp.drink.drinks;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RandomDrinkResponse {

    //Not sure whether to change this to "type" or leave it the same
    private List<Value> drinks;

    @Override
    public String toString() {
        String drinksList = "";
        for (Value drink : drinks) {
            drinksList += drink.getStrDrink() + ": " + drink.getIdDrink();
        }
        return "RandomDrinkResponse{" +
                "drinks=" + drinksList +
                '}';
    }

    public RandomDrinkResponse() {
    }

    public List<Value> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Value> drinks) {
        this.drinks = drinks;
    }
}
