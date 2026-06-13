package com.example.shivamart.controller;

import com.example.shivamart.entity.Payment;
import com.example.shivamart.service.PaymentService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/{orderId}")
    public ResponseEntity<Payment> createPayment(
            @PathVariable Long orderId) {

        return ResponseEntity.ok(
                paymentService.createPayment(orderId));
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PutMapping("/{paymentId}/success")
    public ResponseEntity<Payment> paymentSuccess(
            @PathVariable Long paymentId) {

        return ResponseEntity.ok(
                paymentService.markPaymentSuccess(paymentId));
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PutMapping("/{paymentId}/failed")
    public ResponseEntity<Payment> paymentFailed(
            @PathVariable Long paymentId) {

        return ResponseEntity.ok(
                paymentService.markPaymentFailed(paymentId));
    }
}