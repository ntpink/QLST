package com.qlst.servlet;

import com.qlst.dao.CustomerDAO4;
import com.qlst.dao.DAO4;
import com.qlst.dao.MemberDAO4;
import com.qlst.model.Customer4;
import com.qlst.model.Member4;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.regex.Pattern;

@WebServlet("/register-member")
public class RegisterMemberServlet4 extends HttpServlet {
    private static final Pattern PHONE = Pattern.compile("^\\d{9,11}$");
    private static final Pattern EMAIL = Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");
    private final DAO4 dao = new DAO4();
    private final MemberDAO4 memberDAO = new MemberDAO4();
    private final CustomerDAO4 customerDAO = new CustomerDAO4();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = value(request, "username"), password = value(request, "password");
        String name = value(request, "name"), phone = value(request, "phone");
        String email = value(request, "email"), address = value(request, "address");
        setValues(request, username, name, phone, email, address);
        String validation = validate(username, password, name, phone, email);
        if (validation != null) {
            show(request, response, validation, "error");
            return;
        }
        try (Connection connection = dao.getConnection()) {
            if (memberDAO.getMemberByUserName(connection, username) != null) {
                show(request, response, "Tên đăng nhập đã tồn tại.", "error"); return;
            }
            if (customerDAO.getCustomerByPhone(phone) != null) {
                show(request, response, "Số điện thoại đã tồn tại.", "error"); return;
            }
            if (customerDAO.getCustomerByEmail(email) != null) {
                show(request, response, "Email đã tồn tại.", "error"); return;
            }
            connection.setAutoCommit(false);
            try {
                Member4 member = new Member4(username, password);
                memberDAO.registerMember(connection, member);
                Customer4 customer = new Customer4(name, phone, email, address);
                customer.setMemberId(member.getId());
                customerDAO.createCustomer(connection, customer);
                connection.commit();
                show(request, response, "Đăng ký thành công.", "success");
            } catch (Exception e) {
                connection.rollback();
                throw e;
            }
        } catch (Exception e) {
            getServletContext().log("Đăng ký thành viên thất bại", e);
            show(request, response, "Đăng ký thất bại. Vui lòng thử lại.", "error");
        }
    }

    private String validate(String username, String password, String name, String phone, String email) {
        if (username.isBlank() || password.isBlank() || name.isBlank() || phone.isBlank() || email.isBlank()) return "Vui lòng nhập đầy đủ thông tin bắt buộc.";
        if (!PHONE.matcher(phone).matches()) return "Số điện thoại không hợp lệ.";
        if (!EMAIL.matcher(email).matches()) return "Email không hợp lệ.";
        return null;
    }
    private String value(HttpServletRequest r, String key) { return r.getParameter(key) == null ? "" : r.getParameter(key).trim(); }
    private void setValues(HttpServletRequest r, String u, String n, String p, String e, String a) {
        r.setAttribute("username", u); r.setAttribute("name", n); r.setAttribute("phone", p); r.setAttribute("email", e); r.setAttribute("address", a);
    }
    private void show(HttpServletRequest r, HttpServletResponse s, String message, String type) throws ServletException, IOException {
        r.setAttribute("message", message); r.setAttribute("messageClass", type);
        r.getRequestDispatcher("/RegisterMemberView4.jsp").forward(r, s);
    }
}
