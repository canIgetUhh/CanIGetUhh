package com.drinkapp.drink.customerapp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

public class CustomerController {


    @RestController
    public String login() {
        return "login";
    }    @RequestMapping(path = "api/login", method = RequestMethod.GET)   ///you could also just say @GetMapping


    @RequestMapping(path = "api/signUp", method = RequestMethod.POST)    ///you could also just say @PostMapping
    public String signUp;
        return "signUp";


    @RequestMapping( path = "api/drinkMenu", method = RequestMethod.GET)
        public String drinkMenu;
        return drinkMenu;

    @RequestMapping (path = "api/reviewOrder/:drinkOrderID", method = RequestMethod.POST)
    public String drinkOrderID;
    return drinkOrderID;


    @RequestMapping (path = "api/drinkOrderID/:status")
        public String status;
        return status;




}



 ///drink menu   api/drinkmenu
 //review order / :drink order id        api/:drinkorderid
// timeline/ status       api/ drinkorderID/:status




//    @RequestMapping(path = "/login", method = RequestMethod.GET)
//    public String login () {
//        return "login";
//    }
