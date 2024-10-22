package com.labtest.lab.test.java.service.service;

import com.labtest.lab.test.java.service.entity.Transaction;
import com.labtest.lab.test.java.service.entity.TransactionSummary;
import com.labtest.lab.test.java.service.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public TransactionSummary getLast24HoursSummary() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime yesterday = now.minusHours(24);

        List<Transaction> last24HoursTransactions = transactionRepository.findByTimestampBetween(yesterday, now);

        long totalCustomers = last24HoursTransactions.stream().map(Transaction::getCustomerId).distinct().count();
        long depositCount = last24HoursTransactions.stream().filter(t -> t.getTransactionType().equals("deposit")).count();
        long withdrawalCount = last24HoursTransactions.stream().filter(t -> t.getTransactionType().equals("withdrawal")).count();
        long balanceInquiryCount = last24HoursTransactions.stream().filter(t -> t.getTransactionType().equals("balance_inquiry")).count();

        return new TransactionSummary(totalCustomers, depositCount, withdrawalCount, balanceInquiryCount);
    }

    public List<Transaction> getTransactionByCustomerId(String customerId) {
        return transactionRepository.findByCustomerId(customerId);
    }

    public Transaction addTransaction(Transaction transaction) {
        transaction.setTimestamp(LocalDateTime.now());
        return transactionRepository.save(transaction);
    }
}
