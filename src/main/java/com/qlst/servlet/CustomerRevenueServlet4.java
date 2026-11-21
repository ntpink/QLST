package com.qlst.servlet;

import com.qlst.dao.CustomerDAO4;
import com.qlst.dao.CustomerStatsDAO4;
import com.qlst.dao.TransactionDAO4;
import com.qlst.model.Customer4;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/customer-revenue")
public class CustomerRevenueServlet4 extends HttpServlet {
    private final CustomerStatsDAO4 statsDAO = new CustomerStatsDAO4();
    private final TransactionDAO4 transactionDAO = new TransactionDAO4();
    private final CustomerDAO4 customerDAO = new CustomerDAO4();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession(false) == null || request.getSession(false).getAttribute("manager") == null) {
            response.sendRedirect(request.getContextPath() + "/CustomerMainView4.jsp"); return;
        }
        try {
            String action = request.getParameter("action");
            if ("transactions".equals(action)) showTransactions(request, response);
            else showReport(request, response);
        } catch (Exception e) {
            getServletContext().log("Không tải được báo cáo doanh thu", e);
            throw new ServletException("Không tải được báo cáo doanh thu", e);
        }
    }

    private void showReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LocalDate start = date(request.getParameter("startDate")), end = date(request.getParameter("endDate"));
        request.setAttribute("startDate", text(start)); request.setAttribute("endDate", text(end));
        request.setAttribute("report", statsDAO.getCustomerRevenueReport(start, end));
        request.getRequestDispatcher("/CustomerRevenueReportView4.jsp").forward(request, response);
    }

    private void showTransactions(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        LocalDate start = date(request.getParameter("startDate")), end = date(request.getParameter("endDate"));
        Customer4 customer = customerDAO.getCustomerById(customerId);
        if (customer == null) throw new IllegalArgumentException("Không tìm thấy khách hàng " + customerId);
        request.setAttribute("customerId", customerId); request.setAttribute("customerName", customer.getName());
        request.setAttribute("startDate", text(start)); request.setAttribute("endDate", text(end));
        request.setAttribute("transactions", transactionDAO.getTransactionByCustomer(customerId, start, end));
        request.getRequestDispatcher("/CustomerTransactionsView4.jsp").forward(request, response);
    }

    private LocalDate date(String value) { return value == null || value.isBlank() ? null : LocalDate.parse(value); }
    private String text(LocalDate date) { return date == null ? "" : date.toString(); }
}
