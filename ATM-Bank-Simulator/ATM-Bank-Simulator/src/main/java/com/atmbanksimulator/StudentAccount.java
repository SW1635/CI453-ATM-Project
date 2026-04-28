

package com.atmbanksimulator;

// New class inherits everything from BankAccount
// and overrides withdraw() to change behavoir for student users
public class StudentAccount extends BankAccount {

    // Students have a lower withdrawl limit for safety reasons
    private int dailyLimit = 100;

    public StudentAccount(String accNumber, String accPasswrd, int balance) {
        super(accNumber, accPasswrd, balance); // call parent constructor
    }

    @Override
    public boolean withdraw(int amount) {
        // Check if above amount is over the allowed student limit
        if (amount > dailyLimit) {
            return false; // Deny withdrawl if exceeding the limit
        }
        // Otherwise use the normal withdraw logic from BankAccount
        return super.withdraw(amount);
    }
}