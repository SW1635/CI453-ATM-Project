

package com.atmbanksimulator;

// New class inherits everything from BankAccount
public class SavingsAccount extends BankAccount {

    // Fixed interest rate (5%)
    private double interestRate = 0.05;

    public SavingsAccount(String accNumber, String accPasswrd, int balance) {
        super(accNumber, accPasswrd, balance);
    }

    // Apply interest to current balance
    public void applyInterest() {
        int interest = (int)(getBalance() * interestRate);

        // Add interest to balance using the existing deposit method
        deposit(interest);
    }
}