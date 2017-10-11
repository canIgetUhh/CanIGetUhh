package com.drinkapp.drink.barapp;

import com.drinkapp.drink.customerapp.CustomerRepository;
import com.drinkapp.drink.drinkorders.DrinkOrder;
import com.drinkapp.drink.drinkorders.DrinkOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("api/bar")
public class BartenderController {

    @Autowired
    BartenderRepository bartenderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    DrinkOrderRepository drinkOrderRepository;

//    @PostMapping("/login")
//    public String barLogIn (@RequestBody Bartender bartender, HttpSession session){
//
//        Bartender findBartender = bartenderRepository.findById(bartender.getUsername());
//        if (findBartender == null) {
//            return "That's not a user in our list";
//        }
//        if (BCrypt.checkpw(bartender.getPassword(), findBartender.getPassword())){
//            session.setAttribute("bartenderId", findBartender.getUsername());
//            System.out.println(bartender);
//            return "Bartender is successfully logged in";
//        }
//
//            return "No bartender/password combination exists in our system";
//    }

    @PostMapping("/signUp")
    public String barSignUp (@RequestBody Bartender bartender){

        System.out.println(bartender);

        String username = bartender.getUsername();
        String passwordHash = BCrypt.hashpw(BCrypt.gensalt(12), (bartender.getPassword()));

        Bartender createNewBartender = new Bartender();

        createNewBartender.setUsername(username);
        createNewBartender.setPassword(passwordHash);

        bartenderRepository.save(createNewBartender);

        return "new bartender was created";
    }
}
