package com.app.bankapplication.repository;

import com.app.bankapplication.entity.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class AccountRepository {

    List<Account> accountList = new ArrayList<>();

    public Account save(Account account){
        accountList.add(account);
        return account;
    }

    public List<Account> findAll(){
        return accountList;
    }
}
