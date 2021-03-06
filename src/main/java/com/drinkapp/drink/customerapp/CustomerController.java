package com.drinkapp.drink.customerapp;

import com.drinkapp.drink.HttpUnauthorizedException;
import com.drinkapp.drink.SessionManager;
import com.drinkapp.drink.drinkEntry.DrinkEntry;
import com.drinkapp.drink.drinkEntry.DrinkEntryRepository;
import com.drinkapp.drink.drinks.DrinkRepository;
import com.drinkapp.drink.requests.DrinkOrderRequest;
import com.drinkapp.drink.Status;
import com.drinkapp.drink.drinkOrder.DrinkOrder;
import com.drinkapp.drink.drinkOrder.DrinkOrderRepository;
import com.drinkapp.drink.drinks.AllOfTheDrinks;
import com.drinkapp.drink.drinks.Drink;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("api/customer")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    DrinkOrderRepository drinkOrderRepository;

    @Autowired
    DrinkEntryRepository drinkEntryRepository;

    @Autowired
    DrinkRepository drinkRepository;


    String API_URL = "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=";


    @PostMapping("/login")
    public Integer customerLogIn(@RequestBody Customer customer) {

        Optional<Customer> possibleCustomer = customerRepository.getByUsername(customer.getUsername());

        if (!possibleCustomer.isPresent()) {
//            return "That's not a user in our list";
            throw new HttpUnauthorizedException();
        }

        Customer currentCustomer = possibleCustomer.get();
//        boolean isCorrectPassword = BCrypt.checkpw(customer.getPassword(), currentCustomer.getPassword());
        boolean isCorrectPassword = customer.getPassword().equals(currentCustomer.getPassword());
        if (isCorrectPassword) {

            Integer sessionIdNumber = SessionManager.global.createSession(currentCustomer.getId());
            System.out.println(currentCustomer);
            System.out.println("Current customer sessionID: " + sessionIdNumber);
            return sessionIdNumber;
        }
        throw new HttpUnauthorizedException();
    }


    @CrossOrigin
    @PostMapping("/signup")
    public String customerSignUp(@RequestBody Customer customer) {

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
        System.out.println("Created Customer: " + customer);

        return "new Customer was created";
    }

    @GetMapping("/drink_menu")
    public List<Drink> viewAllDrinks() {
//        show the list of all drinks in the database

        RestTemplate restTemplate = new RestTemplate();
        String url = API_URL;

        AllOfTheDrinks allOfTheDrinks = restTemplate.getForObject(url, AllOfTheDrinks.class);
        System.out.println(allOfTheDrinks);

        return allOfTheDrinks.getDrinks();
    }

    @GetMapping("/drink_menu/{name_of_drink}")
    public List<Drink> searchForDrinkName(@PathVariable String name_of_drink) {
        //Search using the search bar to find what kind of drink you want to order

        RestTemplate restTemplate = new RestTemplate();
        String url = API_URL + name_of_drink;

        AllOfTheDrinks allOfTheDrinks = restTemplate.getForObject(url, AllOfTheDrinks.class);

        System.out.println("ALL THE DRINKS: " + allOfTheDrinks);

        return allOfTheDrinks.getDrinks();
    }

    @PostMapping("/drink_order")
    public DrinkOrder createANewDrinkOrder(@RequestBody DrinkOrderRequest drinkOrderRequest) {
//create a new drink order and add drinks to the order

        SessionManager.SessionInfo sessionInfo = SessionManager.global.getValidSession(drinkOrderRequest.getSessionId());
        System.out.println("The session ID: " + drinkOrderRequest.getSessionId());
        System.out.println("The SessionInfo: " + sessionInfo);
        if (sessionInfo == null) {
            throw new HttpUnauthorizedException();

        } else {
            Customer loggedInCustomer = customerRepository.findById(sessionInfo.userId);


            ArrayList<Drink> newDrinks = drinkOrderRequest.getDrinks();
            System.out.println("-------------\n---------------\nThe drinks: " + newDrinks);

            DrinkOrder drinkOrder = new DrinkOrder();

            drinkOrder.setCustomer(loggedInCustomer);
            List<DrinkEntry> entries = drinkOrderRequest.getDrinkEntries();
            drinkOrder.setDrinkEntries(new HashSet<>(entries));

            for (DrinkEntry entry : entries) {
                Drink existingDrink = drinkRepository.getByIdDrink(entry.getDrink().getIdDrink());
                if (existingDrink != null) {
                    entry.setDrink(existingDrink);
                }
            }

            drinkOrder.setStatus(Status.INITIAL);
            drinkOrderRepository.save(drinkOrder);
            for (DrinkEntry entry : entries) {
                entry.setDrinkOrder(drinkOrder);
                drinkEntryRepository.save(entry);
            }
            System.out.println("This is the created drinkOrder: " + drinkOrder);


            return drinkOrder;
        }
    }

    @GetMapping("/timeline/{orderId}")
    public String drinkTimeline(@PathVariable String orderId) {
//        should change whenever bartender pushes buttons to move through the statuses

        int currentOrderId = Integer.parseInt(orderId);
        DrinkOrder drinkOrder = drinkOrderRepository.findById(currentOrderId);

        System.out.println("This is the current status of drink order: " + drinkOrder.getStatus());

        if (drinkOrder.getStatus() == Status.COMPLETE) {
            System.out.println(drinkOrder.getStatus());
            return "Bartender has completed your order! Please show your ID to the bartender";
        }

        if (drinkOrder.getStatus() == Status.IN_PROGRESS) {
            System.out.println(drinkOrder.getStatus());
            return "Bartender is currently working on your drink order";
        }

        if (drinkOrder.getStatus() == Status.INITIAL) {
            return "Your Drink Order has been received by the bartender";
        }
        throw new IllegalArgumentException();
    }
 }
