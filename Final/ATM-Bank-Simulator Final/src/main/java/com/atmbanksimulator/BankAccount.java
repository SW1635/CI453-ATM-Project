package com.atmbanksimulator;

// ===== 📚🌐BankAccount (Domain / Service / Business Logic) =====

// BankAccount class:
// - Stores instance variables for account number, password, and balance
// - Provides methods to withdraw, deposit, check balance, etc.
public class BankAccount {
    private String accNumber = "";
    private String accPasswd ="";
    private int balance = 0;

    // --- Added: transaction message tracking ---
    // Stores the result of the last transaction (used to show correct messages in the UI
    // for example: "Insufficient funds" for a failed withdraw, "Deposit successful" for a deposit)
    private String lastTransactionMessage = "";

    // --- Added: login attempt tracking ---
    // Counts how many times the wrong password has been entered for this account
    // After 3 failed attempts the account gets locked to prevent brute forcing guessing
    private int failedAttempts = 0;
    private boolean locked = false;

    // Returns true if this account has been locked due to too many failed login attempts
    public boolean isLocked() {
        return locked;
    }

    // Called each time the user enters the wrong password for this account
    // Once failedAtempts reaches 3 the account is locked for the day
    public void recordFailedAttempt() {
        failedAttempts++;
        if (failedAttempts == 3) {  // 3 attempts to get password correct before being locked out
            locked = true;
        }
    }

    // Returns how many failed login attempts have been recorded
    public int getFailedAttempts() {
        return failedAttempts;
    }

    // Resets the failed attempt counter and unlocks the account
    // Called after a successful login or when a new day begins
    public void resetLoginAttempts() {
        failedAttempts = 0;
        locked = false;
    }

    // Returns the result of the last transaction
    // Used by UI to display the correct error/success messages
    public String getLastTransactionMessage() {
        return lastTransactionMessage;
    }

    // "protected" means only this class and subclasses (StudentAccount, PrimeAccount etc)
    // can call this. It lets them update the message without explaining it publicly
    protected void setLastTransactionMessage(String message) {
        this.lastTransactionMessage = message;
    } // --- End of added section ---

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
