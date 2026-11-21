<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>QLST</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/qlst.css">
</head>
<body>
<main class="page">
    <h1>QLST</h1>
    <div class="actions">
        <button type="button"
                onclick="document.getElementById('managerLogin').style.display='block'">
            Đăng nhập
        </button>
        <a class="button"
           href="${pageContext.request.contextPath}/RegisterMemberView4.jsp">
            Đăng ký
        </a>
    </div>

    <form id="managerLogin"
          action="${pageContext.request.contextPath}/manager-login"
          method="post"
          class="form-table"
          style="display:none">
        <table class="form-table">
            <tr>
                <th>Tên đăng nhập:</th>
                <td><input name="username" required></td>
            </tr>
            <tr>
                <th>Mật khẩu:</th>
                <td><input type="password" name="password" required></td>
            </tr>
            <tr>
                <td colspan="2" class="center">
                    <button type="submit">Đăng nhập</button>
                </td>
            </tr>
        </table>
    </form>

    <% if ("1".equals(request.getParameter("loginError"))) { %>
        <p class="message error">
            Tên đăng nhập hoặc mật khẩu không đúng.
        </p>
    <% } %>
</main>
</body>
</html>
