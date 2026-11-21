<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    if (session.getAttribute("manager") == null) {
        response.sendRedirect(
            request.getContextPath() + "/CustomerMainView4.jsp"
        );
        return;
    }
%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>QLST - Quản lý</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/qlst.css">
</head>
<body>
<main class="page">
    <h1>QLST</h1>
    <hr>
    <h2>Quản lý</h2>

    <div class="actions">
        <a class="button"
           href="${pageContext.request.contextPath}/SelectReportView4.jsp">
            Xem báo cáo
        </a>
        <a class="button"
           href="${pageContext.request.contextPath}/logout">
            Đăng xuất
        </a>
    </div>
</main>
</body>
</html>
