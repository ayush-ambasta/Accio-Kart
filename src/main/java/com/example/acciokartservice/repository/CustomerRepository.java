package com.example.acciokartservice.repository;

import com.example.acciokartservice.Enum.Gender;
import com.example.acciokartservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Optional<Customer> findByEmailId(String email);

    List<Customer> findByAge(int age);

    List<Customer> findByGenderAndAge(Gender gender, int age);

    @Query("select count(c) from Customer c where c.age > :a")
    List<Customer> getCustomerCountAgeGreater(@Param("a") int age);
//
//
////    @Query(value = "select count(*) from customer  where gender = :a",nativeQuery = true) //plain sql
    @Query("select count(c) from Customer c where c.gender = :a")
    List<Customer> getCustomerCountGender(@Param("a") Gender gender);

}
