package com.drinkapp.drink;

import com.drinkapp.drink.drinks.RandomDrinkResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DrinkApplication {

    static final String API_URL = "http://www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita";

	public static void main(String[] args) {
		SpringApplication.run(DrinkApplication.class, args);
	}

	@Bean
    public String run() {

        RestTemplate restTemplate = new RestTemplate();
        RandomDrinkResponse randomDrinkResponse = restTemplate.getForObject(API_URL, RandomDrinkResponse.class );
//        System.out.println(randomDrinkResponse);

        System.out.println(randomDrinkResponse);
        System.out.println(randomDrinkResponse.getDrinks().get(0).getStrDrink());


        return "done";
    }


}
