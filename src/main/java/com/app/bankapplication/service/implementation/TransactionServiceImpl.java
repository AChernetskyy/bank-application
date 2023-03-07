package com.app.bankapplication.service.implementation;

import com.app.bankapplication.entity.Account;
import com.app.bankapplication.entity.Transaction;
import com.app.bankapplication.repository.TransactionRepository;
import com.app.bankapplication.service.TransactionService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Component
public class TransactionServiceImpl implements TransactionService {

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    TransactionRepository transactionRepository;


    @Override
    public Transaction makeTransfer(BigDecimal amount, Date creationDate, Account sender, Account receiver, String message) {
        checkAccountOwnership(sender, receiver);

        return null;
    }

    private void checkAccountOwnership(Account sender, Account receiver) {
    }

    @Override
    public List<Transaction> findAll() {
        return null;
    }
}
