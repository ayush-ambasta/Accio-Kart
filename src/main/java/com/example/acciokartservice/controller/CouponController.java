package com.example.acciokartservice.controller;

import com.example.acciokartservice.service.CouponService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coupon")
public class CouponController {

    private final CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @PostMapping
    public ResponseEntity<String> addCoupon(@RequestParam("code") String couponCode,
                                            @RequestParam("discount") double percentageDiscount){
        couponService.addCoupon(couponCode,percentageDiscount);
        return new ResponseEntity<>("Coupon added successfully", HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> allocateCoupon(@RequestParam("email") String email,
                                                 @RequestParam("coupon") String coupon){
        couponService.allocateCoupon(email,coupon);
        return new ResponseEntity<>("Coupon Allocated Successfully !!",HttpStatus.ACCEPTED);
    }
}