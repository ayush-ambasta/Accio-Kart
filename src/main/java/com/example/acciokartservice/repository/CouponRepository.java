package com.example.acciokartservice.repository;

import com.example.acciokartservice.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon,Integer> {

    Optional<Coupon> findByCouponCode(String CouponCode);
}
