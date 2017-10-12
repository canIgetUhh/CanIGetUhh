package com.drinkapp.drink.customerapp;

import com.drinkapp.drink.drinkOrder.DrinkOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
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
//        would I find customer id by hashcode or by equals
        System.out.println(customer.getUsername());
        System.out.println(customer.getPassword());

        Optional<Customer> findCustomer = customerRepository.findById(0);
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

    @PostMapping("/signUp")
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
}
