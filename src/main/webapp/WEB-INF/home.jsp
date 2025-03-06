<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, java.util.ArrayList, model.Attendance, model.User" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ホーム</title>
    <link rel="stylesheet" type="text/css" href="css/home.css">
</head>
<body>
	<div class="container">
    
    <%
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        List<User> userList = (List<User>) request.getAttribute("userList");
    %>
    
    <%
	List<Attendance> lastWeekAttendance = (List<Attendance>) request.getAttribute("lastWeekAttendance");
	%>
    
    <div class="card">
    <h1>ホーム</h1>
    <p>ようこそ、<%= loginUser.getName() %> さん！</p>
    
	<h2>今週の出席状況</h2>
	
	<div class="table-container">
		<table border="1">
    <tr>
        <th>日付</th>
        <th>出席状況</th>
    </tr>
    <% if (lastWeekAttendance != null && !lastWeekAttendance.isEmpty()) { %>
        <% for (Attendance att : lastWeekAttendance) { %>
            <tr>
                <td><%= att.getDate() %></td>
                <td><%= att.getStatus() %></td>
            </tr>
        <% } %>
    <% } else { %>
        <tr>
            <td colspan="3">過去7日間の出席データはありません</td>
        </tr>
    <% } %>
</table>
	</div>
	
	
    <ul>
        <li><a href="users" class="button button-green">ユーザー一覧</a></li>
        <li><a href="attendance" class="button button-green">出席管理</a></li>
    </ul>

    <p><a href="logout" class="button button-red">ログアウト</a></p>
    </div>
    </div>
</body>
</html>
