package com.qlst.dao;

import com.qlst.model.Transaction4;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO4 extends DAO4 {
    public List<Transaction4> getTransactionByCustomer(int customerId, LocalDate startDate, LocalDate endDate) throws SQLException {
        StringBuilder sql = new StringBuilder("SELECT id, transactionDate, totalAmount, status, channel FROM tbltransaction4 WHERE tblCustomer4memberId = ?");
        List<Object> parameters = new ArrayList<>();
        parameters.add(customerId);
        if (startDate != null) {
            sql.append(" AND transactionDate >= ?");
            parameters.add(Timestamp.valueOf(startDate.atStartOfDay()));
        }
        if (endDate != null) {
            sql.append(" AND transactionDate < ?");
            parameters.add(Timestamp.valueOf(endDate.plusDays(1).atStartOfDay()));
        }
        sql.append(" ORDER BY transactionDate");
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql.toString())) {
            for (int i = 0; i < parameters.size(); i++) statement.setObject(i + 1, parameters.get(i));
            try (ResultSet result = statement.executeQuery()) {
                List<Transaction4> rows = new ArrayList<>();
                while (result.next()) rows.add(new Transaction4(result.getInt("id"), result.getTimestamp("transactionDate"),
                        result.getBigDecimal("totalAmount"), result.getString("status"), result.getString("channel")));
                return rows;
            }
        }
    }
}
