

package com.atmbanksimulator;

// New class inherits everything from BankAccount
// and overrides withdraw() to change behavoir for student users
public class StudentAccount extends BankAccount {

    // Students have a lower withdrawl limit for safety reasons
    private int dailyLimit = 100; // Max withdraw per day
    private int withdrawnToday = 0; // Tracks total withdrawn today

    public StudentAccount(String accNumber, String accPasswrd, int balance) {
        super(accNumber, accPasswrd, balance); // call parent constructor
    }

    @Override
    public boolean withdraw(int amount) {

        // Check if the withdrawl amount exceeds the daily limit
        if (withdrawnToday + amount > dailyLimit) {
            setLastTransactionMessage("Daily withdrawl limit reached");
            return false;
        }

        // Try normal withdrawl
        if (super.withdraw(amount)) {
            withdrawnToday += amount; // Track usage
            setLastTransactionMessage("Withdraw sucessful");
            return true;
        } else {
            setLastTransactionMessage("Insufficient funds");
            return false;
        }
    }

    public void resetDailyLimit() {
        withdrawnToday = 0;
    }
}