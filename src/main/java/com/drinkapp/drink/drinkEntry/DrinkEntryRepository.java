package com.drinkapp.drink.drinkEntry;

import com.drinkapp.drink.drinks.Drink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface DrinkEntryRepository extends JpaRepository<DrinkEntry, Integer> {

}
