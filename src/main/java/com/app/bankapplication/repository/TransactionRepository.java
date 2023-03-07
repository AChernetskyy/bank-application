package com.app.bankapplication.repository;

import com.app.bankapplication.entity.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class TransactionRepository {
    public List<Transaction> transactionList = new ArrayList<>();

    public Transaction save(Transaction transaction){
        transactionList.add(transaction);
        return transaction;
    }

    public List<Transaction> findAll(){
        return transactionList;
    }
}
