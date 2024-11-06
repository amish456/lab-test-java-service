package com.labtest.lab.test.java.service.controller;

import com.labtest.lab.test.java.service.entity.Transaction;
import com.labtest.lab.test.java.service.entity.TransactionSummary;
import com.labtest.lab.test.java.service.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@Tag(name = "Transactions", description = "Operations related to customer transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;


    @GetMapping("/summary")
    @Operation(summary = "Get transaction summary", description = "Retrieve the total number of customers and breakdown of transactions in the last 24 hours.")
    public ResponseEntity<TransactionSummary> getTransactionSummary() {
        TransactionSummary summary = transactionService.getLast24HoursSummary();
        return new ResponseEntity<TransactionSummary>(summary, HttpStatus.OK);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<Transaction>> getTransactionsByCustomerId(@PathVariable String id) {
        List<Transaction> transactions = transactionService.getTransactionByCustomerId(id);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<Transaction> addTransactionLog(@RequestBody Transaction transaction) {
        Transaction savedTransaction = transactionService.addTransaction(transaction);
        return new ResponseEntity<>(savedTransaction, HttpStatus.CREATED);
    }

}
