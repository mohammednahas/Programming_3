package com.company;

public class BankAccount2 {

    private String owner;
    private double balance;

    public BankAccount2(String owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {

       // look here
        if (amount > balance) {

            throw new RuntimeException(
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