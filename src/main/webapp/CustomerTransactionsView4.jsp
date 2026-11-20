<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List,com.qlst.model.Transaction4" %>
<% if (session.getAttribute("manager") == null) { response.sendRedirect(request.getContextPath() + "/CustomerMainView4.jsp"); return; } %>
<% List<Transaction4> transactions = (List<Transaction4>) request.getAttribute("transactions"); %>
<!DOCTYPE html>
<html lang="vi"><head><meta charset="UTF-8"><title>QLST - Danh sách giao dịch khách hàng</title><link rel="stylesheet" href="${pageContext.request.contextPath}/css/qlst.css"></head>
<body><main class="page"><h1>QLST</h1><h2>Danh sách giao dịch khách hàng</h2>
<table class="form-table"><tr><th>Mã khách hàng</th><td>${requestScope.customerId}</td></tr><tr><th>Tên khách hàng</th><td>${requestScope.customerName}</td></tr></table><br>
<table class="transaction-table"><tr><th>STT</th><th>Mã GD</th><th>Ngày giao dịch</th><th>Số tiền</th><th>Kênh</th><th>Trạng thái</th></tr>
<% int i = 1; for (Transaction4 row : transactions) { %><tr><td><%= i++ %></td><td><%= row.getTransactionId() %></td><td><%= row.getTransactionDate() %></td><td><%= row.getTotalAmount() %></td><td><%= row.getChannel() %></td><td><%= row.getStatus() %></td></tr><% } %>
</table><div class="actions"><a class="button" href="${pageContext.request.contextPath}/customer-revenue?action=report&startDate=${requestScope.startDate}&endDate=${requestScope.endDate}">Quay lại</a></div></main></body></html>
