package com.drinkapp.drink.drinks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Drink {

    @Id
    int id;

    public String drinkName;

    public String drinkGlass;

    public String drinkIngredients;

    public Drink() {
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public String getDrinkGlass() {
        return drinkGlass;
    }

    public void setDrinkGlass(String drinkGlass) {
        this.drinkGlass = drinkGlass;
    }

    public String getDrinkIngredients() {
        return drinkIngredients;
    }

    public void setDrinkIngredients(String drinkIngredients) {
        this.drinkIngredients = drinkIngredients;
    }

    @Override
    public String toString() {
        return "Drink{" +
                "id=" + id +
                ", drinkName='" + drinkName + '\'' +
                ", drinkGlass='" + drinkGlass + '\'' +
                ", drinkIngredients='" + drinkIngredients + '\'' +
                '}';
    }
}
