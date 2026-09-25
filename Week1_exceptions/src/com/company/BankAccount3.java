package com.company;

public class BankAccount3 {

    private String owner;
    private double balance;

    public BankAccount3(String owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) throws InsufficientBalanceException {

        // look here
        if (amount > balance) {

            throw new InsufficientBalanceException(
                    "Insufficient balance"
            );
        }

        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }

    public String getOwner() {
        return owner;
    }
}