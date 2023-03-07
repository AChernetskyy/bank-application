package com.app.bankapplication.service.implementation;

import com.app.bankapplication.entity.Account;
import com.app.bankapplication.entity.Transaction;
import com.app.bankapplication.enums.AccountType;
import com.app.bankapplication.exception.AccountOwnerShipException;
import com.app.bankapplication.exception.BadRequestException;
import com.app.bankapplication.exception.BalanceNotSufficientException;
import com.app.bankapplication.exception.UnderConstuctionException;
import com.app.bankapplication.repository.AccountRepository;
import com.app.bankapplication.repository.TransactionRepository;
import com.app.bankapplication.service.TransactionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class TransactionServiceImpl implements TransactionService {

    @Value("${under_construction}")
    private boolean isUnderConstruction;

    TransactionRepository transactionRepository;
    AccountRepository accountRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public Transaction makeTransfer(BigDecimal amount, Date creationDate, Account sender, Account receiver, String message) {
        if(!isUnderConstruction){
            checkAccountOwnership(sender, receiver);
            validateAccounts(sender, receiver);
            executeBalanceAndUpdateIfRequired(amount, sender, receiver);

            return transactionRepository.save(Transaction.builder()
                    .amount(amount)
                    .creationDate(creationDate)
                    .sender(sender.getId())
                    .receiver(receiver.getId())
                    .message(message).build());
        }else {
            throw new UnderConstuctionException("Site is under construction");
        }

    }

    private void executeBalanceAndUpdateIfRequired(BigDecimal amount, Account sender, Account receiver) {
        if (checkSenderBalance(sender, amount)) {
            sender.setBalance(sender.getBalance().subtract(amount));
            receiver.setBalance(receiver.getBalance().add(amount));
        } else {
            throw new BalanceNotSufficientException("Balance is not sufficient for transfer");
        }
    }

    private boolean checkSenderBalance(Account sender, BigDecimal balance) {
        return sender.getBalance().subtract(balance).compareTo(BigDecimal.ZERO) > 0;
    }

    private void validateAccounts(Account sender, Account receiver) {
        if (sender == null || receiver == null) {
            throw new BadRequestException("Sender or receiver can not be null");
        }
        if (sender.getId().equals(receiver.getId())) {
            throw new BadRequestException("Can not make transfer to the same account");
        }
        findAccountById(sender.getId());
        findAccountById(receiver.getId());
    }

    private Account findAccountById(UUID accountId) {
        return accountRepository.findById(accountId);
    }

    private void checkAccountOwnership(Account sender, Account receiver) {
        if ((sender.getAccountType().equals(AccountType.SAVINGS) || receiver.getAccountType().equals(AccountType.SAVINGS)) && !sender.getUserId().equals(receiver.getUserId())) {
            throw new AccountOwnerShipException("When one of the account type is Saving, sender and receiver has to be the same person");
        }
    }

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }
}
