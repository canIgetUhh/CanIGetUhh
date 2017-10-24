package com.drinkapp.drink.barapp;

import com.drinkapp.drink.HttpUnauthorizedException;
import com.drinkapp.drink.SessionManager;
import com.drinkapp.drink.Status;
import com.drinkapp.drink.customerapp.CustomerRepository;
import com.drinkapp.drink.drinkOrder.DrinkOrder;
import com.drinkapp.drink.drinkOrder.DrinkOrderRepository;
import com.drinkapp.drink.drinks.Drink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/bar")
public class BartenderController {

    @Autowired
    BartenderRepository bartenderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    DrinkOrderRepository drinkOrderRepository;


    @PostMapping("/login")
    public Integer barLogIn (@RequestBody Bartender bartender){

        Optional <Bartender> findBartender = bartenderRepository.getByUsername(bartender.getUsername());

        if (!findBartender.isPresent()) {
            throw new HttpUnauthorizedException();
        }

        Bartender currentBartender = findBartender.get();
//        boolean isCorrectPassword = BCrypt.checkpw(bartender.getPassword(), findBartender.getPassword());
        boolean isCorrectPassword = bartender.getPassword().equals(currentBartender.getPassword());
        if (isCorrectPassword){
            Integer sessionIdNumber = SessionManager.global.createSession(currentBartender.getId());
            System.out.println(currentBartender);

            return sessionIdNumber;
        }

             throw new HttpUnauthorizedException();
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

        List<DrinkOrder> findIntitalDrinkOrders = drinkOrderRepository.findAll();

        return findIntitalDrinkOrders.stream()
                .filter(drinkOrder -> drinkOrder.getStatus() == Status.INITIAL)
                .collect(Collectors.toList());
    }

    @GetMapping("/current_orders/{orderId}")
    public DrinkOrder currentOrder (@PathVariable String orderId){
//        finds a single drinkOrder with all drink items

        int currentId = Integer.parseInt(orderId);
        DrinkOrder openOrder = drinkOrderRepository.findById(currentId);

        if (openOrder.getStatus() == Status.INITIAL){
            openOrder.setStatus(Status.IN_PROGRESS);
            drinkOrderRepository.save(openOrder);
        }

//        JButton doneButton = new JButton("Drink Complete");
//        doneButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (openOrder.getStatus() == Status.IN_PROGRESS){
//                    openOrder.setStatus(Status.COMPLETE);
//                    drinkOrderRepository.save(openOrder);
//                }
//            }
//        });


        return openOrder;
    }

    @GetMapping("/completed_orders")
    public List<DrinkOrder> completedOrders (){

       List<DrinkOrder> findCompletedDrinkOrders = drinkOrderRepository.findAll();

        return findCompletedDrinkOrders.stream()
                .filter(drinkOrder -> drinkOrder.getStatus() == Status.COMPLETE)
                .collect(Collectors.toList());
    }
}