package com.qlst.model;

import java.math.BigDecimal;

public class CustomerStats4 {
    private final int customerId;
    private final String customerName;
    private final long transactionCount;
    private final BigDecimal totalRevenue;

    public CustomerStats4(int customerId, String customerName, long transactionCount, BigDecimal totalRevenue) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.transactionCount = transactionCount;
        this.totalRevenue = totalRevenue;
    }

    public int getCustomerId() { return customerId; }
    public String getCustomerName() { return customerName; }
    public long getTransactionCount() { return transactionCount; }
    public BigDecimal getTotalRevenue() { return totalRevenue; }
}
