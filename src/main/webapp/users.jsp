<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, model.User" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ユーザー一覧</title>
    <link rel="stylesheet" type="text/css" href="css/users.css">
</head>
<body>
    <%
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        List<User> userList = (List<User>) request.getAttribute("userList");
    %>
    
    <div class="container">
    <h1>ユーザー一覧</h1>
    <p>ようこそ、 <%= loginUser.getName() %> さん！</p>
	
	<div class="table-container">
    <!-- 6年のユーザー一覧 -->
        <h2>6年生</h2>
        <table>
            <tr>
                <th>名前</th>
                <th>学年</th>
            </tr>
            <% for (User user : userList) { %>
                <% if (user.getGrade() == 6) { %>
                    <tr>
                        <td><%= user.getName() %></td>
                        <td><%= user.getGrade() %></td>
                    </tr>
                <% } %>
            <% } %>
        </table>
        
        <!-- 5年のユーザー一覧 -->
        <h2>5年生</h2>
        <table>
            <tr>
                <th>名前</th>
                <th>学年</th>
            </tr>
            <% for (User user : userList) { %>
                <% if (user.getGrade() == 5) { %>
                    <tr>
                        <td><%= user.getName() %></td>
                        <td><%= user.getGrade() %></td>
                    </tr>
                <% } %>
            <% } %>
        </table>
    </div>

    <p><a href="home" class="button button-green">ホームに戻る</a></p>
    </div>
</body>
</html>
