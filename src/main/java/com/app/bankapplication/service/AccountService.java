package com.app.bankapplication.service;

import com.app.bankapplication.entity.Account;
import com.app.bankapplication.enums.AccountType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface AccountService {
    Account createNewAccount(BigDecimal balance, Date creationDate, AccountType accountType, Long userId );

    List<Account> listAllAccount();

}
