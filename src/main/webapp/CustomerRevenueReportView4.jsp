<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List,com.qlst.model.CustomerStats4" %>

<%
    if (session.getAttribute("manager") == null) {
        response.sendRedirect(
            request.getContextPath() + "/CustomerMainView4.jsp"
        );
        return;
    }

    List<CustomerStats4> report =
        (List<CustomerStats4>) request.getAttribute("report");
%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>QLST - Thống kê khách hàng theo doanh thu</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/qlst.css">
</head>
<body>
<main class="page">
    <h1>QLST</h1>
    <h2>Thống kê khách hàng theo doanh thu</h2>
    <hr>

    <form class="report-filter"
          action="${pageContext.request.contextPath}/customer-revenue"
          method="get">
        <input type="hidden" name="action" value="report">

        <label>
            Từ ngày:
            <input type="date"
                   name="startDate"
                   value="${requestScope.startDate}">
        </label>

        <label>
            Đến ngày:
            <input type="date"
                   name="endDate"
                   value="${requestScope.endDate}">
        </label>

        <button type="submit">Thống kê</button>
    </form>

    <table class="report-table">
        <tr>
            <th>STT</th>
            <th>Mã KH</th>
            <th>Tên khách hàng</th>
            <th>Số giao dịch</th>
            <th>Tổng doanh thu</th>
            <th>Chi tiết</th>
        </tr>

        <%
            int i = 1;
            for (CustomerStats4 row : report) {
        %>
        <tr>
            <td><%= i++ %></td>
            <td><%= row.getCustomerId() %></td>
            <td><%= row.getCustomerName() %></td>
            <td><%= row.getTransactionCount() %></td>
            <td><%= row.getTotalRevenue() %></td>
            <td>
                <a href="${pageContext.request.contextPath}/customer-revenue?action=transactions&customerId=<%= row.getCustomerId() %>&startDate=${requestScope.startDate}&endDate=${requestScope.endDate}">
                    Xem giao dịch
                </a>
            </td>
        </tr>
        <% } %>
    </table>

    <div class="actions">
        <a class="button"
           href="${pageContext.request.contextPath}/SelectReportView4.jsp">
            Quay lại
        </a>
    </div>
</main>
</body>
</html>
