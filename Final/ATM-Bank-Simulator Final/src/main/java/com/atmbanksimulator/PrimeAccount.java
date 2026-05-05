

package com.atmbanksimulator;

// PrimeAccount extends BankAccount and overrides withdraw() to allow an overdraft
// Prime users can spend up to £200 beyond their actual balance per day
// Same use of @Override as StudentAccount, we researched this to allow
// each account type to have its own version of withdraw()
public class PrimeAccount extends BankAccount {

    // Prime users are allowed to go into overdraft (£200 maximum total)
    private int overdraftLimit = 200;

    // Tracks how much overdraft has been used so far
    private int usedOverdraft = 0;

    public PrimeAccount(String accNumber, String accPasswrd, int balance) {
        super(accNumber, accPasswrd, balance);
    }

    // Overrides the standard withdraw() from BankAccount
    // Allows withdrawls beyond the balance, up to the remaining overdraft allowance
    // for example: balance=30, usedOverdraft=0 means you can withdraw up to 230 (30+200)
    @Override
    public boolean withdraw(int amount) {

        // Check for invalid input
        if (amount < 0) {
            setLastTransactionMessage("Invalid amount");
                return false;
        }

        int balance = getBalance();

        // Total available = balance + remaining overdraft
        int available = balance + (overdraftLimit - usedOverdraft);

        // If trying to take more than allowed
        if (amount > available) {
            setLastTransactionMessage("Overdraft limit exceeded");
            return false;
        }

        // If withdrawl uses overdraft, track it
        if (amount > balance) {
            usedOverdraft += (amount - balance);
        }

        // Withdraw whatever is in actual balance before moving onto overdraft
        super.withdraw(Math.min(amount, balance));

        setLastTransactionMessage("Withdraw successful");
            return true;
    }

    // Reset overdraft usage (via "next day" button)
    public void resetOverdraft() {
        usedOverdraft = 0;
    }
}