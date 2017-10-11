package com.drinkapp.drink.drinkorders;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class DrinkOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    public String drinkName;

    public String drinkGlass;

    public String drinkIngredients;

//    This would keep track of all items the customer creates when sending order
    @ManyToMany
    public List<DrinkOrder> allDrinkOrders;


}
