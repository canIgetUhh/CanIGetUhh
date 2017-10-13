package com.drinkapp.drink;

import com.drinkapp.drink.drinks.AllOfTheDrinks;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DrinkApplication {

    static final String API_URL = "http://www.thecocktaildb.com/api/json/v1/1/search.php?s=";

	public static void main(String[] args) {
		SpringApplication.run(DrinkApplication.class, args);
	}

	@Bean
    public String run() {

        String url = API_URL + "Margarita";
        RestTemplate restTemplate = new RestTemplate();
        AllOfTheDrinks allOfTheDrinks = restTemplate.getForObject(url, AllOfTheDrinks.class );
//        System.out.println(allOfTheDrinks);

        System.out.println(allOfTheDrinks);
        System.out.println(allOfTheDrinks.getDrinks().get(0).getStrDrink());


        return "done";
    }


}
