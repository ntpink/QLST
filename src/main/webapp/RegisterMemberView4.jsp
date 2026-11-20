<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head><meta charset="UTF-8"><title>QLST - Đăng ký thành viên</title><link rel="stylesheet" href="${pageContext.request.contextPath}/css/qlst.css"></head>
<body>
<main class="page">
    <h1>QLST</h1><hr><h2>Đăng ký thành viên</h2>
    <% String message = (String) request.getAttribute("message"); String messageClass = (String) request.getAttribute("messageClass"); %>
    <% if (message != null) { %><p class="message <%= messageClass %>"><%= message %></p><% } %>
    <form action="${pageContext.request.contextPath}/register-member" method="post" autocomplete="off">
        <table class="form-table">
            <tr><th>Tên đăng nhập <span class="required-mark">*</span>:</th><td><input name="username" autocomplete="new-username" value="<%= request.getAttribute("username") == null ? "" : request.getAttribute("username") %>" required></td></tr>
            <tr><th>Mật khẩu <span class="required-mark">*</span>:</th><td><input type="password" name="password" autocomplete="new-password" required></td></tr>
            <tr><th>Họ và tên <span class="required-mark">*</span>:</th><td><input name="name" value="<%= request.getAttribute("name") == null ? "" : request.getAttribute("name") %>" required></td></tr>
            <tr><th>Số điện thoại <span class="required-mark">*</span>:</th><td><input name="phone" type="tel" autocomplete="tel" value="<%= request.getAttribute("phone") == null ? "" : request.getAttribute("phone") %>" required></td></tr>
            <tr><th>Email <span class="required-mark">*</span>:</th><td><input type="email" name="email" autocomplete="email" value="<%= request.getAttribute("email") == null ? "" : request.getAttribute("email") %>" required></td></tr>
            <tr><th>Địa chỉ:</th><td><input name="address" value="<%= request.getAttribute("address") == null ? "" : request.getAttribute("address") %>"></td></tr>
            <tr><td colspan="2" class="center"><button type="submit">Đăng ký</button> <a class="button" href="${pageContext.request.contextPath}/CustomerMainView4.jsp">Hủy</a></td></tr>
        </table>
    </form>
</main>
</body>
</html>
