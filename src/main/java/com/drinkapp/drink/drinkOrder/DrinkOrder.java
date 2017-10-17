package com.drinkapp.drink.drinkOrder;

import com.drinkapp.drink.Status;
import com.drinkapp.drink.barapp.Bartender;
import com.drinkapp.drink.customerapp.Customer;
import com.drinkapp.drink.drinks.Drink;

import javax.persistence.*;
import java.util.ArrayList;

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

    ArrayList<Drink> drinks;

    Status status;

    public DrinkOrder() {
    }

    public DrinkOrder(Customer customer, Bartender bartender, ArrayList<Drink> drinks, Status status) {
        this.customer = customer;
        this.bartender = bartender;
        this.drinks = drinks;
        this.status = status;
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

    public ArrayList<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(ArrayList<Drink> drinks) {
        this.drinks = drinks;
    }

    public int getOrderId() {
        return orderId;
    }

    private void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "DrinkOrder{" +
                "orderId=" + orderId +
                ", customer=" + customer +
                ", bartender=" + bartender +
                ", drinks=" + drinks +
                ", status=" + status +
                '}';
    }
}
