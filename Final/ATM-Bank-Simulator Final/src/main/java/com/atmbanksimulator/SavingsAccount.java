

package com.atmbanksimulator;

// SavingsAccount extends BankAccount
// It doesn't change how withdraw() or deposit() work.
// The only extra feature is interest, which is applied per day via applyInterest()
public class SavingsAccount extends BankAccount {

    // Fixed interest rate (5%)
    private double interestRate = 0.05;

    public SavingsAccount(String accNumber, String accPasswrd, int balance) {
        super(accNumber, accPasswrd, balance);
    }

    // Calculates interest based on the current balance and deposits it
    // for example: balance=200, rate=0.05 means the interest=10, this is added to the balance
    // The cast to (int) rounds down, so 0.50 interest would be dropped,
    // which keeps the balance as a whole number (matches how BankAccount stores it)
    public void applyInterest() {
        int interest = (int)(getBalance() * interestRate);

        // Add interest to balance using the existing deposit method
        deposit(interest);

        setLastTransactionMessage("Intrest applied +" + interest);
    }

    //Getter so UI can display the interest rate
    public double getInterestRate() {
        return interestRate;
    }
}