package com.example.acciokartservice.service;

import com.example.acciokartservice.Enum.Gender;
import com.example.acciokartservice.dto.request.CustomerRequest;
import com.example.acciokartservice.dto.response.CustomerResponse;
import com.example.acciokartservice.exception.CustomerNotFoundException;
import com.example.acciokartservice.model.Customer;
import com.example.acciokartservice.repository.CustomerRepository;
import com.example.acciokartservice.service.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    public CustomerResponse addCustomer(CustomerRequest customerRequest){


        Customer customer= CustomerTransformer.customerRequestToCustomer(customerRequest);
        Customer savedCustomer=customerRepository.save(customer);
        return CustomerTransformer.customerToCustomerResponse(savedCustomer);
    }

    public CustomerResponse getCustomerID(int customerId) {
        Optional<Customer>optionalCustomer=customerRepository.findById(customerId);
        if(optionalCustomer.isEmpty()){
            throw new CustomerNotFoundException("Invalid CustomerId Not Found");
        }
        return CustomerTransformer.customerToCustomerResponse(optionalCustomer.get());
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

    public List<Customer> getCustomerCountGender(Gender gender) {
        return customerRepository.getCustomerCountGender(gender);
    }

    public void deleteCustomer(String email) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmailId(email);
        if(optionalCustomer.isEmpty()){
            throw new CustomerNotFoundException("Customer doesn't exisit");
        }
        // deletes customer, its identity and its orders
        customerRepository.delete(optionalCustomer.get());
    }
}
