package com.app.bankapplication;

import com.app.bankapplication.entity.Account;
import com.app.bankapplication.enums.AccountType;
import com.app.bankapplication.service.AccountService;
import com.app.bankapplication.service.TransactionService;
import com.app.bankapplication.service.implementation.AccountServiceImpl;
import com.app.bankapplication.service.implementation.TransactionServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootApplication
public class BankApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(BankApplication.class, args);
        AccountService accountService = applicationContext.getBean(AccountServiceImpl.class);
        TransactionService transactionService = applicationContext.getBean(TransactionServiceImpl.class);

        Account receiver = accountService.createNewAccount( BigDecimal.TEN, new Date(), AccountType.CHECKINGS, 1L);
        Account sender =  accountService.createNewAccount(new BigDecimal(70), new Date(), AccountType.CHECKINGS, 2L);

        accountService.listAllAccount().forEach(System.out::println);

        transactionService.makeTransfer(BigDecimal.TEN, new Date(), sender, receiver, "transfer no:1");

        System.out.println(transactionService.findAll().get(0));
        accountService.listAllAccount().forEach(System.out::println);

        transactionService.makeTransfer(new BigDecimal(5), new Date(), sender, receiver, "transfer no:2");

        System.out.println(transactionService.findAll().get(1));
        accountService.listAllAccount().forEach(System.out::println);
    }

}
