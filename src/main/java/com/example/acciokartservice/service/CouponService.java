package com.example.acciokartservice.service;

import com.example.acciokartservice.exception.CouponNotFoundException;
import com.example.acciokartservice.exception.CustomerNotFoundException;
import com.example.acciokartservice.model.Coupon;
import com.example.acciokartservice.model.Customer;
import com.example.acciokartservice.repository.CouponRepository;
import com.example.acciokartservice.repository.CustomerRepository;
import com.example.acciokartservice.utils.CustomerValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepo;
    private final CustomerValidation customerValidation;
    private final CustomerRepository customerRepository;

    /*public CouponService(CouponRepository couponRepo) {
        this.couponRepo = couponRepo;
    }*/

    public void addCoupon(String couponCode,double percentageDiscount) {
        Coupon coupon = Coupon.builder()
                .couponCode(couponCode)
                .percentageDiscount(percentageDiscount)
                .build();
        couponRepo.save(coupon);
    }

    public void allocateCoupon(String email, String couponCode) {
        if(!customerValidation.isValid(email)){
            throw new CustomerNotFoundException("Invalid Email !!");
        }
        Optional<Coupon> optionalCoupon = couponRepo.findByCouponCode(couponCode);
        if(optionalCoupon.isEmpty()) {
            throw new CouponNotFoundException("Invalid Coupon !!");
        }
        Coupon coupon = optionalCoupon.get();
        Customer customer = customerRepository.findByEmailId(email).get();

        coupon.getCustomers().add(customer);
        couponRepo.save(coupon);
    }
}