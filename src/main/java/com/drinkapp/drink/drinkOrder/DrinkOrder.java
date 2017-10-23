package com.drinkapp.drink.drinkOrder;

import com.drinkapp.drink.Status;
import com.drinkapp.drink.barapp.Bartender;
import com.drinkapp.drink.customerapp.Customer;
import com.drinkapp.drink.drinkEntry.DrinkEntry;
import com.drinkapp.drink.drinks.Drink;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;

@Entity
@Table(name = "drinkorders")
public class DrinkOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @OneToOne
    Customer customer;

    @OneToOne
    Bartender bartender;

//    ArrayList<Drink> drinks;

    Status status;

    @JsonProperty
    @OneToMany(mappedBy = "drinkOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<DrinkEntry> drinkEntries;

    public DrinkOrder() {
    }

    public DrinkOrder(int orderId, Customer customer, Bartender bartender, Status status, Set<DrinkEntry> drinkEntries) {
        this.orderId = orderId;
        this.customer = customer;
        this.bartender = bartender;
        this.status = status;
        this.drinkEntries = drinkEntries;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Bartender getBartender() {
        return bartender;
    }

    public void setBartender(Bartender bartender) {
        this.bartender = bartender;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

//    public ArrayList<Drink> getDrinks() {
//        return drinks;
//    }
//
//    public void setDrinks(ArrayList<Drink> drinks) {
//        this.drinks = drinks;
//    }

    public int getOrderId() {
        return orderId;
    }

    private void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Set<DrinkEntry> getDrinkEntries() {
        return drinkEntries;
    }

    public void setDrinkEntries(Set<DrinkEntry> drinkEntries) {
        this.drinkEntries = drinkEntries;
    }

    @Override
    public String toString() {
        return "DrinkOrder{" +
                "orderId=" + orderId +
                ", customer=" + customer +
                ", bartender=" + bartender +
                ", status=" + status +
                ", drinkEntries=" + drinkEntries +
                '}';
    }
}
