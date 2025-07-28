package com.transactionservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/accounts")
public class AccountTransactionController {
    private static final Logger logger = LoggerFactory.getLogger(AccountTransactionController.class);

    @Autowired
    private AccountTransactionService accountTransactionService;

    @PutMapping("/updateBalance/{userId}")
    public ResponseEntity<Void> updateBalance(@PathVariable String userId, @RequestParam double amount) {
        logger.info("Updating balance for userId: {}, amount: {}", userId, amount);
        try {
            accountTransactionService.updateBalance(userId, amount);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            logger.error("Error updating balance: {}", e.getMessage());
            throw new ResponseStatusException(
                e.getMessage().contains("not found") ? HttpStatus.NOT_FOUND : HttpStatus.BAD_REQUEST,
                e.getMessage()
            );
        } catch (Exception e) {
            logger.error("Unexpected error updating balance: {}", e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating balance: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        logger.info("Creating account for userId: {}", account.getUserId());
        try {
            Account savedAccount = accountTransactionService.createAccount(account);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedAccount);
        } catch (RuntimeException e) {
            logger.error("Error creating account: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}