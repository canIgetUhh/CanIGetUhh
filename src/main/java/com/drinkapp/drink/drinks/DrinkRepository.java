package com.drinkapp.drink.drinks;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrinkRepository extends JpaRepository<Drink, Integer> {
//    void add(Drink drink);
//    Drink getById (int idDrink);
    Drink findByStrDrink (String strDrink);
//    List<Drink> getAll();
//    void delete(int idDrink);
//    void delete (List<Integer> ids);
}
