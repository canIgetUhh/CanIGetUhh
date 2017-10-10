package com.drinkapp.drink.drinkorders;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class DrinkOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;



//    This would keep track of all items the customer creates when sending order
    public List<DrinkOrder> allDrinkOrders;


}
