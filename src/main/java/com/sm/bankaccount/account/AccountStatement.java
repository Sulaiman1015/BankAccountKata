package com.sm.bankaccount.account;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
@Data
@AllArgsConstructor
public class AccountStatement {
    private Date date;
    private double amount;
    private double balance;
}
