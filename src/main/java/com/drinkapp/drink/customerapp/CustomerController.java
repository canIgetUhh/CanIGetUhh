package com.drinkapp.drink.customerapp;

import com.drinkapp.drink.drinkOrder.DrinkOrderRepository;
import com.drinkapp.drink.drinks.AllOfTheDrinks;
import com.drinkapp.drink.drinks.Drink;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/customer")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    DrinkOrderRepository drinkOrderRepository;


    String API_URL = "http://www.thecocktaildb.com/api/json/v1/1/search.php?s=";


    @PostMapping("/login")
    public String customerLogIn (@RequestBody Customer customer, HttpSession session){
        System.out.println(customer.getUsername());
        System.out.println(customer.getPassword());

        Optional<Customer> findCustomer = customerRepository.getByUsername(customer.getUsername());
        if (!findCustomer.isPresent()){
            return "That's not a user in our list";
        }
        Customer currentCustomer = findCustomer.get();
//        boolean isCorrectPassword = BCrypt.checkpw(customer.getPassword(), currentCustomer.getPassword());
        boolean isCorrectPassword = customer.getPassword() == currentCustomer.getPassword();
        if (isCorrectPassword){
            session.setAttribute("customerId", findCustomer.hashCode());
            System.out.println(customer);

            return "Customer is successfully logged in";
        }
        return "No user/password combination exists in our system";
    }

    @PostMapping("/signup")
    public String customerSignUp (@RequestBody Customer customer){

        System.out.println(customer);

        String firstName = customer.getFirstName();
        String lastName = customer.getLastName();
        String email = customer.getEmail();
        String username = customer.getUsername();
//        String passwordHash = BCrypt.hashpw(BCrypt.gensalt(12), (customer.getPassword()));
        String passwordHash = customer.getPassword();
        String dob = customer.getDob();

        Customer createNewCustomer = new Customer();

        createNewCustomer.setFirstName(firstName);
        createNewCustomer.setLastName(lastName);
        createNewCustomer.setEmail(email);
        createNewCustomer.setUsername(username);
        createNewCustomer.setPassword(passwordHash);
        createNewCustomer.setDob(dob);

        customerRepository.save(createNewCustomer);

        return "new user was created";
    }

    //Search using the search bar to find what kind of drink you want to order
    @GetMapping("/drink_menu")
    public List<Drink> searchForDrinkName (){

        RestTemplate restTemplate = new RestTemplate();
        String url = API_URL + "Mojito";

        AllOfTheDrinks allOfTheDrinks = restTemplate.getForObject(url, AllOfTheDrinks.class );

        System.out.println("ALL THE DRINKS: " + allOfTheDrinks);

        return allOfTheDrinks.getDrinks();
    }
}
