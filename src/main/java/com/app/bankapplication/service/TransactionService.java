package com.app.bankapplication.service;

import com.app.bankapplication.entity.Account;
import com.app.bankapplication.entity.Transaction;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface TransactionService {
    Transaction makeTransfer(BigDecimal amount, Date creationDate, Account sender, Account receiver, String message);

    List<Transaction> findAll();
}
