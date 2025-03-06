<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, model.Attendance, java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>出席登録</title>
    <link rel="stylesheet" type="text/css" href="css/attendance.css">
</head>
<body>
	
	<div class="container">
    <h1>出席登録</h1>
    <form action="<%= request.getContextPath() %>/attendance" method="post">
    	<div class="form-group">
        <label>日付:
            <input type="date" name="date" required>
        </label><br>
        </div>
        
        <div class="form-group">
        <label>出席状況:
            <select name="status">
                <option value="出席">出席</option>
                <option value="欠席">欠席</option>
            </select>
        </label><br>
        </div>
        
        <button type="submit" class="button primary">登録</button>
    </form>

    <h2>出席履歴</h2>
    
    <div class="table-container">
    <table border="1">
        <tr>
            <th>日付</th>
            <th>出席状況</th>
        </tr>
        <%
            List<Attendance> list = (List<Attendance>) request.getAttribute("attendanceList");
            if (list == null) {
                list = new ArrayList<>();
            }

            if (list.isEmpty()) {
        %>
            <tr><td colspan="2">出席データがありません</td></tr>
        <%
            } else {
                for (Attendance att : list) {
        %>
            <tr>
                <td><%= att.getDate() %></td>
                <td><%= att.getStatus() %></td>
            </tr>
        <%
                }
            }
        %>
    </table>
    </div>

    <p><a href="home" class="button button-green">ホームに戻る</a></p>
    </div>
</body>
</html>
