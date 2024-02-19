package com.example.acciokartservice.service;

import com.example.acciokartservice.Enum.Gender;
import com.example.acciokartservice.exception.CustomerNotFoundException;
import com.example.acciokartservice.model.Customer;
import com.example.acciokartservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    public Customer addCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public Customer getCustomerID(int customerId) {
        Optional<Customer>optionalCustomer=customerRepository.findById(customerId);
        if(optionalCustomer.isEmpty()){
            throw new CustomerNotFoundException("Invalid CustomerId Not Found");
        }
        return optionalCustomer.get();
    }

    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    public Customer getCustomerByEmail(String email) {
        Optional<Customer>optionalCustomer=customerRepository.findByEmailId(email);
        if(optionalCustomer.isEmpty()){
            throw new CustomerNotFoundException("Invalid Email");
        }
        return optionalCustomer.get();
    }

    public List<Customer> getCustomerByAge(int age) {
        return customerRepository.findByAge(age);
    }

    public List<Customer> getCustomerByGenderAndAge(Gender gender, int age) {
        return customerRepository.findByGenderAndAge(gender,age);
    }

    public List<Customer> getCustomerCountAgeGreater(int age) {
        return customerRepository.getCustomerCountAgeGreater(age);
    }

//    public List<Customer> getCustomerCountGender(Gender gender) {
//        return customerRepository.getCustomerCountGender(gender);
//    }
}
