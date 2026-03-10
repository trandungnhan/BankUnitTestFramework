package com.bank.qa;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Payment Service Unit Tests")
public class PaymentServiceTest extends BaseTest{

    private PaymentService paymentService;

    @BeforeEach
    void setUp(){
        paymentService = new PaymentService();
    }

    @Test
    @DisplayName("TC01: Should approve payment with limit")
    void shouldApprovePaymentWithinLimit(){
        boolean status = paymentService.processPayment(2500);
        assertTrue(status,"Payment of 2500 should be approved");
    }

    @Test
    @DisplayName("TC02: Should decline payment exceeding limit")
    void shouldDeclinePaymentOverLimit(){
        boolean status = paymentService.processPayment(7000);
        assertFalse(status,"Payment of 7000 should be declined");
    }

    @ParameterizedTest
    @DisplayName("TC03: Should throw exception for invalid amount")
    @ValueSource(doubles = {0,-1})
    void shouldThrowExceptionForZeroAmount(double invalidAmount){
        Exception exception = assertThrows(IllegalArgumentException.class, () ->{
           paymentService.processPayment(invalidAmount);
        });
        assertEquals("Amount must be greater than zero", exception.getMessage());
    }
}
