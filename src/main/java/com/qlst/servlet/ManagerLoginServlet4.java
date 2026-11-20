package com.qlst.servlet;

import com.qlst.dao.MemberDAO4;
import com.qlst.model.Member4;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/manager-login")
public class ManagerLoginServlet4 extends HttpServlet {
    private final MemberDAO4 memberDAO = new MemberDAO4();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Member4 member = memberDAO.authenticate(request.getParameter("username"), request.getParameter("password"));
            if (member != null) {
                request.getSession(true).setAttribute("manager", member);
                response.sendRedirect(request.getContextPath() + "/ManagerMainView4.jsp");
            } else response.sendRedirect(request.getContextPath() + "/CustomerMainView4.jsp?loginError=1");
        } catch (SQLException e) {
            getServletContext().log("Đăng nhập quản lý thất bại", e);
            response.sendRedirect(request.getContextPath() + "/CustomerMainView4.jsp?loginError=1");
        }
    }
}
