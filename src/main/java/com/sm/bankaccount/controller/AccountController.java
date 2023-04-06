package com.sm.bankaccount.controller;

import com.sm.bankaccount.account.Account;
import com.sm.bankaccount.account.AccountStatement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private Map<String, Account> accounts = new HashMap<>();

    // Deposit money into the account
    @PostMapping("/{accountId}/deposit")
    public void deposit(@PathVariable String accountId, @RequestParam double amount) {
        Account account = getOrCreateAccount(accountId);
        account.deposit(amount);
    }

    // Withdraw money from the account
    @PostMapping("/{accountId}/withdraw")
    public void withdraw(@PathVariable String accountId, @RequestParam double amount) {
        Account account = getOrCreateAccount(accountId);
        account.withdraw(amount);
    }

    // Generate an account statement
    @GetMapping("/{accountId}/statement")
    public List<AccountStatement> getStatement(@PathVariable String accountId) {
        Account account = getOrCreateAccount(accountId);
        return account.getStatement();
    }

    // method to get or create an account
    private Account getOrCreateAccount(String accountId) {
        if (!accounts.containsKey(accountId)) {
            accounts.put(accountId, new Account(accountId));
        }
        return accounts.get(accountId);
    }
}
