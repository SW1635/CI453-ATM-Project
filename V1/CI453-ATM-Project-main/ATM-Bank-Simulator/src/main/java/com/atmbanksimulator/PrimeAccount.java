

package com.atmbanksimulator;

// New class inherits everything from BankAccount
// and overrides withdraw() to change behavoir for prime users
public class PrimeAccount extends BankAccount {

    // Prime users are allowed to go into overdraft
    private int overdraftLimit = 200;

    public PrimeAccount(String accNumber, String accPasswrd, int balance) {
        super(accNumber, accPasswrd, balance);
    }

    @Override
    public boolean withdraw(int amount) {
        // Allow withdrawl even if bank balance is not enough,
        // as long as its within the overdraft limit
        if (amount >= 0 && (getBalance() + overdraftLimit) >= amount) {

            // If the normal withdrawl works, use it
            if (super.withdraw(amount)) {
                return true;
            }

            // If balance isnt enough, simulate an overdraft
            // (we allow the overdraft without rejecting)
            return true;
        }
        return false;
    }
}