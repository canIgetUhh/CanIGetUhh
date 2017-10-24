package com.drinkapp.drink.drinkEntry;

import com.drinkapp.drink.drinkOrder.DrinkOrder;
import com.drinkapp.drink.drinks.Drink;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "drinkentries")
public class DrinkEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_drink")
    Drink drink;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "drink_order_id")
    @JsonIgnore
    DrinkOrder drinkOrder;

    int quantity;


    public DrinkEntry() {
    }

    public DrinkEntry(Drink drink, int quantity) {
        this.drink = drink;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public DrinkOrder getDrinkOrder() {
        return drinkOrder;
    }

    public void setDrinkOrder(DrinkOrder drinkOrder) {
        this.drinkOrder = drinkOrder;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "DrinkEntry{" +
                "id=" + id +
                ", drink=" + drink.getStrDrink() +
                ", drinkOrder=" + drinkOrder.getOrderId() +
                ", quantity=" + quantity +
                '}';
    }
}
