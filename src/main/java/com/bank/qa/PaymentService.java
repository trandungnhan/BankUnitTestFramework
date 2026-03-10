package com.bank.qa;

public class PaymentService {
    public boolean processPayment(double amount){
        if(amount <= 0){
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        return amount <= 5000;
    }
}
