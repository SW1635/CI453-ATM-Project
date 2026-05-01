package com.atmbanksimulator;

// ===== 📚🌐BankAccount (Domain / Service / Business Logic) =====

// BankAccount class:
// - Stores instance variables for account number, password, and balance
// - Provides methods to withdraw, deposit, check balance, etc.
public class BankAccount {
    private String accNumber = "";
    private String accPasswd ="";
    private int balance = 0;

    // Stores the result of the last transaction (used to correct UI for Overdraws + Withdraw limits)
    private String lastTransactionMessage = "";

    // Returns the result of the last transaction
    // Used by UI to display the correct error/success messages
    public String getLastTransactionMessage() {
        return lastTransactionMessage;
    }

    protected void setLastTransactionMessage(String message) {
        this.lastTransactionMessage = message;
    }

    public BankAccount() {}
    public BankAccount(String a, String p, int b) {
        accNumber = a;
        accPasswd = p;
        balance = b;
    }

    // Withdraw money from this account
    // Returns true if successful, or false if the amount is negative or exceeds the current balance
    public boolean withdraw( int amount ) {

        // Check for invalid input
        if (amount < 0) {
            lastTransactionMessage = "Invalid amount";
                return false;
        }

        // Check if enough balance is available
        if (balance < amount) {
            lastTransactionMessage = "Insufficient funds";
                return false;
        }

        // Peform a withdrawl
        balance = balance - amount;
        lastTransactionMessage = "Withdrawl successful";
            return true;
    }

    // deposit the amount of money into this account.
    // Return true if successful,or false if the amount is negative
    public boolean deposit( int amount ) {

        if (amount < 0) {
            lastTransactionMessage = "Invalid amount";
                return false;
        }

        balance = balance + amount;
        lastTransactionMessage = "Deposit successful";
            return true;
    }

    // Getter for the account balance
    // Returns the current balance of this account
    public int getBalance() {
        return balance;
    }

    // Getter for the account number
    public String getAccNumber() {
        return accNumber;
    }
    // Getter for the account password
    public String getaccPasswd() {
        return accPasswd;
    }
}
