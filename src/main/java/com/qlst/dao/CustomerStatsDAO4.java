package com.qlst.dao;

import com.qlst.model.CustomerStats4;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerStatsDAO4 extends DAO4 {
    private static final String SUCCESS_STATUS = "Thành công";

    public List<CustomerStats4> getCustomerRevenueReport(LocalDate startDate, LocalDate endDate) throws SQLException {
        StringBuilder sql = new StringBuilder("""
                SELECT c.memberId, c.name, COUNT(t.id) AS transactionCount,
                       COALESCE(SUM(t.totalAmount), 0) AS totalRevenue
                FROM tblcustomer4 c
                JOIN tbltransaction4 t ON t.tblCustomer4memberId = c.memberId
                WHERE t.status = ?
                """);
        List<Object> parameters = new ArrayList<>();
        parameters.add(SUCCESS_STATUS);
        appendDateFilter(sql, parameters, startDate, endDate);
        sql.append(" GROUP BY c.memberId, c.name ORDER BY totalRevenue DESC, c.memberId");
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql.toString())) {
            bind(statement, parameters);
            try (ResultSet result = statement.executeQuery()) {
                List<CustomerStats4> rows = new ArrayList<>();
                while (result.next()) {
                    rows.add(new CustomerStats4(result.getInt("memberId"), result.getString("name"),
                            result.getLong("transactionCount"), result.getBigDecimal("totalRevenue")));
                }
                return rows;
            }
        }
    }

    private void appendDateFilter(StringBuilder sql, List<Object> parameters, LocalDate start, LocalDate end) {
        if (start != null) {
            sql.append(" AND t.transactionDate >= ?");
            parameters.add(Timestamp.valueOf(start.atStartOfDay()));
        }
        if (end != null) {
            sql.append(" AND t.transactionDate < ?");
            parameters.add(Timestamp.valueOf(end.plusDays(1).atStartOfDay()));
        }
    }

    private void bind(PreparedStatement statement, List<Object> parameters) throws SQLException {
        for (int i = 0; i < parameters.size(); i++) statement.setObject(i + 1, parameters.get(i));
    }
}
