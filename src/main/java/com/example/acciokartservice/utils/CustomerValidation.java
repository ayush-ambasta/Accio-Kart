package com.example.acciokartservice.utils;

import com.example.acciokartservice.model.Customer;
import com.example.acciokartservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomerValidation {
    private final CustomerRepository customerRepository;

    public Boolean isValid(String email){
        Optional<Customer> customer = customerRepository.findByEmailId(email);
        return customer.isPresent();
    }

}
