package com.qlst.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Transaction4 {
    private final int transactionId;
    private final Timestamp transactionDate;
    private final BigDecimal totalAmount;
    private final String status;
    private final String channel;

    public Transaction4(int transactionId, Timestamp transactionDate, BigDecimal totalAmount, String status, String channel) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.totalAmount = totalAmount;
        this.status = status;
        this.channel = channel;
    }

    public int getTransactionId() { return transactionId; }
    public Timestamp getTransactionDate() { return transactionDate; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public String getStatus() { return status; }
    public String getChannel() { return channel; }
}
