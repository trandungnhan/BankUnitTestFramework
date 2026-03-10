package com.bank.qa;

public class AccountService {
    private double balance;

    public AccountService(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount){
        if(amount <= 0){
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if(amount <= 0){
            throw new IllegalArgumentException("Withdraw amount must be positive");
        }
        if (amount > balance){
            throw new InsufficientFundsException("Insufficient funds for this withdrawal");
        }
        balance -= amount;
    }
}
