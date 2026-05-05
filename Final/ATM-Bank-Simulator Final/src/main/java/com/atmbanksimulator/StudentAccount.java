

package com.atmbanksimulator;

// StudentAccount extends BankAccount. It inherits everything (deposit, balance, login tracking, etc)
// adds a daily withdrawl limit on top.
// Researched the @Override annotation to make this functional, it tells Java that withdraw() here is intentionally
// replacing the version in BankAccount, rather than being a brand new method on accident
public class StudentAccount extends BankAccount {

    // Students have a lower withdrawl limit for safety reasons
    private int dailyLimit = 100; // Max withdraw per day
    private int withdrawnToday = 0; // Tracks total withdrawn today

    // "super(...)" passes the account details up to BankAccount's constructor
    // so we don't have to re-declare accNumber, password and balance here
    public StudentAccount(String accNumber, String accPasswrd, int balance) {
        super(accNumber, accPasswrd, balance); // call parent constructor
    }

    // Overrides the standard withdrawl() from BankAccount
    // Before doing the normal withdrawl, it checks the daily limit hasn't been hit
    // If the limit is fine, it calls super.withdraw() to do the actual balance check
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

    // Resets the daily counter back to zero, called by Bank.nextDay() when "Day" is pressed
    public void resetDailyLimit() {
        withdrawnToday = 0;
    }
}