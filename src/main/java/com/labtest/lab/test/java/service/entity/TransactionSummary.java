package com.labtest.lab.test.java.service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionSummary {
    private long totalCustomers;
    private long depositCount;
    private long withdrawalCount;
    private long balanceInquiryCount;

}
