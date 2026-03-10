package com.bank.qa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Account Service Unit Tests")
public class AccountServiceTest extends BaseTest {

    private AccountService accountService;

    @BeforeEach
    void setUp() {
        accountService = new AccountService(1000.0);
    }

    @Test
    @DisplayName("TC01: Verify deposit increases balance")
    void shouldIncreaseBalanceAfterDeposit() {
        accountService.deposit(500.0);
        assertEquals(1500.0, accountService.getBalance(), "Balance should be 1500.0");
    }

    @Test
    @DisplayName("TC02: Verify withdrawal decreases balance")
    void shouldDecreaseBalanceAfterWithdrawal() throws InsufficientFundsException {
        accountService.withdraw(400.0);
        assertEquals(600.0, accountService.getBalance(), "Balance should be 600 after withdrawing 400");
    }

    @Test
    @DisplayName("TC03: Verify withdrawal fails when balance is insufficient")
    void shouldThrowExceptionWhenFundsAreInsufficient() {
        Exception exception = assertThrows(InsufficientFundsException.class, () -> {
            accountService.withdraw(2000.0);
        });
        assertEquals("Insufficient funds for this withdrawal", exception.getMessage());
    }

    @ParameterizedTest
    @DisplayName("TC04: Verify withdrawal fails for invalid amount")
    @ValueSource(doubles = {-100.0, -1.0, 0.0})
    void shouldThrowExceptionForInvalidWithdrawAmount(double invalidAmount) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            accountService.withdraw(invalidAmount);
        });
        assertEquals("Withdraw amount must be positive", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1, 0.0})
    @DisplayName("TC05: Verify deposit fails for zero or negative amounts")
    void shouldThrowExceptionForInvalidDepositedAmount(double invalidAmount) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            accountService.deposit(invalidAmount);
        });
        assertEquals("Deposit amount must be positive", exception.getMessage());
    }
}
