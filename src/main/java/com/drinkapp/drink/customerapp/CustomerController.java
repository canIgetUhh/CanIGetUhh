package com.drinkapp.drink.customerapp;

import com.drinkapp.drink.Requests.DrinkOrderRequest;
import com.drinkapp.drink.Status;
import com.drinkapp.drink.drinkOrder.DrinkOrder;
import com.drinkapp.drink.drinkOrder.DrinkOrderRepository;
import com.drinkapp.drink.drinks.AllOfTheDrinks;
import com.drinkapp.drink.drinks.Drink;

import com.drinkapp.drink.drinks.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
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
    public String customerLogIn(@RequestBody Customer customer, HttpSession session) {
        System.out.println(customer.getUsername());
        System.out.println(customer.getPassword());

        Optional<Customer> findCustomer = customerRepository.getByUsername(customer.getUsername());
        if (!findCustomer.isPresent()) {
            return "That's not a user in our list";
        }
        Customer currentCustomer = findCustomer.get();
//        boolean isCorrectPassword = BCrypt.checkpw(customer.getPassword(), currentCustomer.getPassword());
        boolean isCorrectPassword = customer.getPassword() == currentCustomer.getPassword();
        if (isCorrectPassword) {
            session.setAttribute("customerId", findCustomer.hashCode());
            System.out.println(customer);

            return "Customer is successfully logged in";
        }
        return "No user/password combination exists in our system";
    }

    @CrossOrigin
    @PostMapping("/signup")
    public String customerSignUp(@RequestBody Customer customer) {

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

    @GetMapping("/view_all_drinks")
    public List<Drink> viewAllDrinks (){
//        show the list of all drinks in the database

        RestTemplate restTemplate = new RestTemplate();
        String url = API_URL;

        AllOfTheDrinks allOfTheDrinks = restTemplate.getForObject(url, AllOfTheDrinks.class);
        System.out.println(allOfTheDrinks);

        return allOfTheDrinks.getDrinks();
    }

    @GetMapping("/drink_menu")
    public List<Drink> searchForDrinkName() {
        //Search using the search bar to find what kind of drink you want to order

        RestTemplate restTemplate = new RestTemplate();
        String url = API_URL + "Mojito";

        AllOfTheDrinks allOfTheDrinks = restTemplate.getForObject(url, AllOfTheDrinks.class);

        System.out.println("ALL THE DRINKS: " + allOfTheDrinks);

        return allOfTheDrinks.getDrinks();
    }

    @PostMapping("/drink_order")
    public DrinkOrder createANewDrinkOrder(@RequestBody DrinkOrderRequest drinkOrderRequest) {
//create a new drink order and add drinks to the order

        ArrayList<Drink> newDrinks = drinkOrderRequest.getDrinks();
        System.out.println("-------------\n---------------\nThe drinks: " + newDrinks);
        DrinkOrder drinkOrder = new DrinkOrder();

//        for (Drink newDrink : newDrinkOrder) {
////            newDrink.getIdDrink();
//            newDrinkOrder.add(newDrink);
////            drinkRepository.save(newDrink);
//            System.out.println("This drink was saved: " + newDrink);
//        }
//        drinkOrder.setCustomer(customer);
//        drinkOrder.setDrinks(newDrinkOrder);
        drinkOrder.setDrinks(newDrinks);
        drinkOrder.setStatus(Status.INITIAL);
        drinkOrderRepository.save(drinkOrder);
        System.out.println("This is the created drinkOrder: " + drinkOrder);

        return drinkOrder;
    }

//    @GetMapping("/timeline/{orderId}")
//    public String drinkTimeline(@PathParam("orderId") DrinkOrder drinkOrder) {
////        should change whenever bartender pushes buttons to move through the statuses
//
//        System.out.println(drinkOrder.getStatus());
//        if (drinkOrder.getStatus() == Status.IN_PROGRESS) {
//            System.out.println(drinkOrder.getStatus());
//            return "Bartender is currently working on your drink order";
//        }
//        if (drinkOrder.getStatus() == Status.COMPLETE) {
//            System.out.println(drinkOrder.getStatus());
//            return "Bartender has completed your order! Please show your ID to the bartender";
//        }
//        return "Your Drink Order has been received by the bartender";
//    }
}
