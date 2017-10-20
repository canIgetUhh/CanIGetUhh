package com.drinkapp.drink.barapp;

import com.drinkapp.drink.Status;
import com.drinkapp.drink.customerapp.CustomerRepository;
import com.drinkapp.drink.drinkOrder.DrinkOrder;
import com.drinkapp.drink.drinkOrder.DrinkOrderRepository;
import com.drinkapp.drink.drinks.Drink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping("api/bar")
public class BartenderController {

    @Autowired
    BartenderRepository bartenderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    DrinkOrderRepository drinkOrderRepository;

      List<DrinkOrder> initialDrinkOrders = new ArrayList<>();
//
//    List<DrinkOrder> inProgressDrinkOrders = new ArrayList<>();
//
//    List<DrinkOrder> completedDrinkOrders = new ArrayList<>();


    @PostMapping("/login")
    public String barLogIn (@RequestBody Bartender bartender, HttpSession session){

        System.out.println(bartender.getUsername());
        System.out.println(bartender.getPassword());

        Optional <Bartender> findBartender = bartenderRepository.getByUsername(bartender.getUsername());

        if (!findBartender.isPresent()) {
            return "That's not a user in our list";
        }

        Bartender currentBartender = findBartender.get();
//        boolean isCorrectPassword = BCrypt.checkpw(bartender.getPassword(), findBartender.getPassword());
        boolean isCorrectPassword = bartender.getPassword().equals(bartender.getPassword());
        if (isCorrectPassword){
            session.setAttribute("bartenderId", findBartender.hashCode());
            System.out.println(bartender);

            return "Bartender is successfully logged in";
        }

            return "No bartender/password combination exists in our system";
    }

    @PostMapping("/signup")
    public String barSignUp (@RequestBody Bartender bartender){

        System.out.println(bartender);

        String username = bartender.getUsername();
//        String passwordHash = BCrypt.hashpw(BCrypt.gensalt(12), (bartender.getPassword()));
        String passwordHash = bartender.getPassword();

        Bartender createNewBartender = new Bartender();

        createNewBartender.setUsername(username);
        createNewBartender.setPassword(passwordHash);

        bartenderRepository.save(createNewBartender);

        return "new bartender was created";
    }

    @GetMapping("/current_orders")
    public List<DrinkOrder> allCurrentOrders (){
//      get a list of all the current drinkOrders available

        while (true) {
            drinkOrderRepository.findAll();
        }

    }

//    @GetMapping("/current_order/{orderId}")
//    public DrinkOrder currentOrder (DrinkOrder drinkOrder){
////        finds a single drinkOrder with all drink items
//
//        DrinkOrder openOrder = drinkOrderRepository.findById(drinkOrder.getOrderId());
//
//        initialDrinkOrders.remove(openOrder);
//        openOrder.setStatus(Status.IN_PROGRESS);
//        inProgressDrinkOrders.add(openOrder);
//        openOrder.getDrinks();
//
//        return openOrder;
//    }

//    @GetMapping("/completed_orders")
//    public DrinkOrder completedOrders (DrinkOrder drinkOrder){
//
//        if (drinkOrder.getStatus() == Status.IN_PROGRESS){
//            inProgressDrinkOrders.remove(drinkOrder);
//            drinkOrder.setStatus(Status.COMPLETE);
//            completedDrinkOrders.add(drinkOrder);
//        }
//        return drinkOrder;
//    }
}