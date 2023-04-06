package com.sm.bankaccount.account;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
public class Account {
    private String accountId;
    private double balance;
    private List<AccountStatement> statements;
    // Constructor
    public Account(String accountId) {
        this.accountId = accountId;
        this.balance = 0.00;
        this.statements = new ArrayList<>();
    }

    // Deposit
    public void deposit(double amount) {
        balance += amount;
        statements.add(new AccountStatement(new Date(), amount, balance));
    }

    // Withdraw
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            statements.add(new AccountStatement(new Date(), -amount, balance));
        } else {
            throw new RuntimeException("Insufficient funds");
        }
    }

    // Generate an account statement
    public List<AccountStatement> getStatement() {
        return statements;
    }
}
