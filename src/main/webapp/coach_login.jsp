<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>コーチログイン</title>
    <link rel="stylesheet" type="text/css" href="css/coach_login.css">
</head>
<body>
    <div class="login-container">
        <h2>コーチログイン</h2>

        <form action="CoachLoginServlet" method="post">
        	<div class="input-group">
            <label>パスワード:</label>
            <input type="password" name="coachPassword" required class="input-field">
            </div>
            <input type="submit" value="ログイン" class="login-button">
        </form>

        <p><a href="index.jsp" class="back-link">ログイン画面へ戻る</a></p>

        <!-- エラーメッセージ表示 -->
        <% String error = (String) request.getAttribute("error"); %>
        <% if (error != null) { %>
            <p class="error-message"><%= error %></p>
        <% } %>
    </div>
</body>
</html>
