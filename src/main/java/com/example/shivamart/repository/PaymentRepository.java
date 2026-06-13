package com.example.shivamart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.shivamart.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
  
}
