package com.example.acciokartservice.service.transformer;

import com.example.acciokartservice.dto.request.CustomerRequest;
import com.example.acciokartservice.dto.response.CustomerResponse;
import com.example.acciokartservice.model.Customer;

public class CustomerTransformer {
    public static Customer customerRequestToCustomer(CustomerRequest customerRequest){

        return Customer.builder()
                .name(customerRequest.getName())
                .age(customerRequest.getAge())
                .gender(customerRequest.getGender())
                .emailId(customerRequest.getEmailId())
                .city(customerRequest.getCity())
                .build();

    }

    public static CustomerResponse customerToCustomerResponse(Customer customer){

        return CustomerResponse.builder()
                .name(customer.getName())
                .emailId(customer.getEmailId())
                .createdAt(customer.getCreated())
                .build();
    }
}
