package com.example.shivamart.service.impl;

import com.example.shivamart.entity.*;
import com.example.shivamart.repository.*;

import com.example.shivamart.service.PaymentService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl
        implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    @Override
    public Payment createPayment(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new RuntimeException("Order not found"));

        Payment payment = new Payment();

        payment.setOrder(order);
        payment.setAmount(order.getTotalAmount());
        payment.setPaymentDate(LocalDateTime.now());
        payment.setStatus(PaymentStatus.PENDING);

        payment.setTransactionId(
                UUID.randomUUID().toString());

        return paymentRepository.save(payment);
    }

    @Override
    public Payment markPaymentSuccess(
            Long paymentId) {

        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() ->
                        new RuntimeException("Payment not found"));

        payment.setStatus(PaymentStatus.SUCCESS);

        payment.getOrder()
                .setStatus(OrderStatus.PLACED);

        return paymentRepository.save(payment);
    }

    @Override
    public Payment markPaymentFailed(
            Long paymentId) {

        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() ->
                        new RuntimeException("Payment not found"));

        payment.setStatus(PaymentStatus.FAILED);

        return paymentRepository.save(payment);
    }
}