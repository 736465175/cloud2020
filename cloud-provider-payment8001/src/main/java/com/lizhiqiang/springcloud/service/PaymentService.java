package com.lizhiqiang.springcloud.service;

import com.lizhiqiang.springcloud.entities.Payment;

public interface PaymentService {

    int create(Payment payment);

    Payment getPaymentById(Long id);
}
