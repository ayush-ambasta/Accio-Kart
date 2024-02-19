package com.example.acciokartservice.controller;

import com.example.acciokartservice.Enum.Gender;
import com.example.acciokartservice.model.Customer;
import com.example.acciokartservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @PostMapping("/add")
    public Customer addCustomer(@RequestBody Customer customer){
        return customerService.addCustomer(customer);
    }

    @GetMapping("/get/id/{id}")
    public Customer getCustomerID(@PathVariable("id") int customerId) {
        return customerService.getCustomerID(customerId);
    }

    @GetMapping("/get/all")
    public List<Customer> getAllCustomer(){
        return customerService.getAllCustomer();
    }

    @GetMapping("/get/email/{email}")
    public Customer getCustomerByEmail(@PathVariable("email") String email){
        return customerService.getCustomerByEmail(email);
    }

    @GetMapping("/get/age/{age}")
    public List<Customer> getCustomerByAge(@PathVariable("age") int age){
        return customerService.getCustomerByAge(age);
    }

    @GetMapping("/get/GenderAndAge")
    public List<Customer> getCustomerByGenderAndAge(@RequestParam("gender") Gender gender,@RequestParam("age") int age){
        return customerService.getCustomerByGenderAndAge(gender,age);
    }

    @GetMapping("/get/count-age")
    public List<Customer> getCustomerCountAgeGreater(@RequestParam("age") int age){
        return customerService.getCustomerCountAgeGreater(age);
    }
//
//    @GetMapping("/get/count-gender")
//    public List<Customer> getCustomerCountGender(@RequestParam("gender") Gender gender){
//        return customerService.getCustomerCountGender(gender);
//    }
}
