package com.transactionservice;

import com.transactionservice.Account;
import com.transactionservice.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AccountTransactionService {
    private static final Logger logger = LoggerFactory.getLogger(AccountTransactionService.class);

    @Autowired
    private AccountRepository accountRepository;

    public void updateBalance(String userId, double amount) {
        logger.debug("Updating balance for userId: {}, amount: {}", userId, amount);
        Account account = accountRepository.findByUserId(userId)
            .orElseThrow(() -> {
                logger.error("Account not found: {}", userId);
                return new RuntimeException("Account not found: " + userId);
            });
        double newBalance = account.getBalance() + amount;
        if (newBalance < 0) {
            logger.error("Insufficient balance for userId: {}, current: {}, requested: {}", userId, account.getBalance(), amount);
            throw new RuntimeException("Insufficient balance");
        }
        account.setBalance(newBalance);
        accountRepository.save(account);
        logger.info("Balance updated successfully for userId: {}, new balance: {}", userId, newBalance);
    }

    public Account createAccount(Account account) {
        logger.debug("Creating account for userId: {}", account.getUserId());
        if (account.getUserId() == null || account.getUserId().trim().isEmpty()) {
            throw new RuntimeException("User ID is required");
        }
        if (accountRepository.findByUserId(account.getUserId()).isPresent()) {
            throw new RuntimeException("Account already exists for userId: " + account.getUserId());
        }
        if (account.getBalance() < 0) {
            throw new RuntimeException("Initial balance cannot be negative");
        }
        // Set default values if not provided
        if (account.getTypeOfUser() == null) {
            account.setTypeOfUser("customer");
        }
        if (account.getPassword() == null) {
            account.setPassword("default_password");
        }
        return accountRepository.save(account);
    }
}