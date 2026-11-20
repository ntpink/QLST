package com.qlst.dao;

import com.qlst.model.Customer4;
import java.sql.*;

public class CustomerDAO4 extends DAO4 {
    public Customer4 getCustomerByPhone(String phone) throws SQLException {
        return findOne("phone", phone);
    }

    public Customer4 getCustomerByEmail(String email) throws SQLException {
        return findOne("email", email);
    }

    private Customer4 findOne(String column, String value) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT memberId, name, phone, email, address FROM tblcustomer4 WHERE " + column + " = ?")) {
            statement.setString(1, value);
            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) return null;
                return readCustomer(result);
            }
        }
    }

    public void insertCustomer(Connection connection, Customer4 customer) throws SQLException {
        String sql = "INSERT INTO tblcustomer4 (memberId, name, phone, email, address) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, customer.getMemberId());
            statement.setString(2, customer.getName());
            statement.setString(3, customer.getPhone());
            statement.setString(4, customer.getEmail());
            statement.setString(5, customer.getAddress());
            statement.executeUpdate();
        }
    }

    public Customer4 getCustomerById(int memberId) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT memberId, name, phone, email, address FROM tblcustomer4 WHERE memberId = ?")) {
            statement.setInt(1, memberId);
            try (ResultSet result = statement.executeQuery()) {
                return result.next() ? readCustomer(result) : null;
            }
        }
    }

    private Customer4 readCustomer(ResultSet result) throws SQLException {
        Customer4 customer = new Customer4(result.getString("name"), result.getString("phone"),
                result.getString("email"), result.getString("address"));
        customer.setMemberId(result.getInt("memberId"));
        return customer;
    }
}
