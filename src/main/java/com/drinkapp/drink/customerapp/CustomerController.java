package com.drinkapp.drink.customerapp;

import com.drinkapp.drink.Status;
import com.drinkapp.drink.drinkOrder.DrinkOrder;
import com.drinkapp.drink.drinkOrder.DrinkOrderRepository;
import com.drinkapp.drink.drinks.Drink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/login")
    public String customerLogIn (@RequestBody Customer customer, HttpSession session){
        System.out.println(customer.getUsername());
        System.out.println(customer.getPassword());

        Optional<Customer> findCustomer = customerRepository.getByUserName(customer.getUsername());
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

    @GetMapping("/drink_menu")
    public Drink drinkMenu (Drink allDrinks){
//        shows list of all drinks available to select


        return allDrinks;
    }

    @PostMapping("/drink_menu")
    public Drink addDrinkToCart (Drink drink, List<DrinkOrder> newDrinks){
//        create a new drinkOrder, allow to add multiple drinks into cart, set status as initial
        DrinkOrder newDrinkOrder = new DrinkOrder();

        newDrinkOrder.setStatus(Status.INITIAL);
        newDrinks.add(newDrinkOrder);

        return newDrinkOrder;
    }

    @GetMapping("/drink_order/:orderId")
    public DrinkOrder drinkOrder (DrinkOrder drinkOrder){
//      shows individual drinkOrder and the items in the drinkOrder

        DrinkOrder currentDrinkOrder = drinkOrderRepository.getById(drinkOrder.getOrderId());

        return currentDrinkOrder;
    }

    @GetMapping("/timeline/:orderId/:status")
    public String drinkTimeline (DrinkOrder drinkOrder){
//        should change whenever bartender pushes button to move the statuses

        if (drinkOrder.getStatus() == Status.IN_PROGRESS){
            return "Bartender is currently working on your drink order";
        }
        if (drinkOrder.getStatus() == Status.COMPLETE){
            return "Bartender has completed your order! Please show your ID to the bartender";
        }
        return "Your Drink Order has been received by the bartender";
    }
}
