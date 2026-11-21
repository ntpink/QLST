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
    <title>QLST - Chọn loại báo cáo</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/qlst.css">
</head>
<body>
<main class="page">
    <h1>QLST</h1>
    <hr>
    <h2>Chọn loại báo cáo</h2>

    <form action="${pageContext.request.contextPath}/customer-revenue"
          method="get">
        <input type="hidden" name="action" value="report">

        <table class="choice-table">
            <tr>
                <td>
                    <input type="radio" name="report" value="revenue" required>
                </td>
                <td>Thống kê khách hàng theo doanh thu</td>
            </tr>
            <tr>
                <td>
                    <input type="radio" name="report" value="time" disabled>
                </td>
                <td>Thống kê doanh thu theo thời gian</td>
            </tr>
            <tr>
                <td>
                    <input type="radio" name="report" value="product" disabled>
                </td>
                <td>Thống kê sản phẩm bán chạy</td>
            </tr>
        </table>

        <div class="actions">
            <button type="submit">Tiếp tục</button>
            <a class="button"
               href="${pageContext.request.contextPath}/ManagerMainView4.jsp">
                Quay lại
            </a>
        </div>
    </form>
</main>
</body>
</html>
