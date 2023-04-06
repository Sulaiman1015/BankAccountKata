package com.sm.bankaccount;

import com.sm.bankaccount.controller.AccountController;
import com.sm.bankaccount.account.Account;
import com.sm.bankaccount.account.AccountStatement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {
    @InjectMocks
    private AccountController accountController;

    @Mock
    private Map<String, Account> accounts;

    @Test
    void testDeposit() {
        Account account = new Account("1010");
        Mockito.when(accounts.get("1010")).thenReturn(account);
        accountController.deposit("1010", 1000.0);
        Assertions.assertEquals(1000.0, account.getBalance(), 0.00);
    }

    @Test
    void testWithdraw() {
        Account account = new Account("1010");
        account.deposit(1000.0);
        Mockito.when(accounts.get("1010")).thenReturn(account);
        accountController.withdraw("1010", 500.0);
        Assertions.assertEquals(500.0, account.getBalance(), 0.00);
    }

    @Test
    void testGetStatement() {
        Account account = new Account("1010");
        account.deposit(1000.0);
        account.withdraw(500.0);
        Mockito.when(accounts.get("1010")).thenReturn(account);
        List<AccountStatement> statement = accountController.getStatement("1010");
        Assertions.assertEquals(2, statement.size());
        Assertions.assertEquals(1000.0, statement.get(0).getAmount(), 0.00);
        Assertions.assertEquals(-500.0, statement.get(1).getAmount(), 0.00);
    }
}
