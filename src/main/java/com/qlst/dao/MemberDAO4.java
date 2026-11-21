package com.qlst.dao;

import com.qlst.model.Member4;
import java.sql.*;

public class MemberDAO4 extends DAO4 {
    public Member4 getMemberByUserName(String username) throws SQLException {
        try (Connection connection = getConnection()) {
            return getMemberByUserName(connection, username);
        }
    }

    public Member4 getMemberByUserName(Connection connection, String username) throws SQLException {
        String sql = "SELECT id, username, password FROM tblmember4 WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) return null;
                Member4 member = new Member4(result.getString("username"), result.getString("password"));
                member.setId(result.getInt("id"));
                return member;
            }
        }
    }

    public Member4 authenticate(String username, String password) throws SQLException {
        String sql = "SELECT id, username, password FROM tblmember4 WHERE username = ? AND password = ?";
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) return null;
                Member4 member = new Member4(result.getString("username"), result.getString("password"));
                member.setId(result.getInt("id"));
                return member;
            }
        }
    }

    public int registerMember(Connection connection, Member4 member) throws SQLException {
        String sql = "INSERT INTO tblmember4 (username, password) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, member.getUsername());
            statement.setString(2, member.getPassword());
            statement.executeUpdate();
            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (!keys.next()) throw new SQLException("Không lấy được memberId sau khi đăng ký");
                member.setId(keys.getInt(1));
                return member.getId();
            }
        }
    }
}
