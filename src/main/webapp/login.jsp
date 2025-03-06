<% // このjspは使わない %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ログイン</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<div class="login-container">
	<img src="images/kmwzt32f.png" alt="チームロゴ" class="logo">
    <h1>ログイン</h1>

    <!-- エラーメッセージを表示 -->
    <% if (request.getAttribute("error") != null) { %>
        <p style="color: red;"><%= request.getAttribute("error") %></p>
    <% } %>

    <form action="login" method="post">
    	<div class="input-group">
    		<label for="email">メールアドレス</label>
        	<input type="text" id="email" name="email" placeholder=" " required>
    	</div>

    	<div class="input-group">
    		<label for="password">パスワード</label>
        	<input type="password" id="password" name="password" placeholder=" " required>
    	</div>

    	<button type="submit">ログイン</button>
    </form>

    <a href="#">パスワードをお忘れですか？</a>
    <a href="register.jsp">新規ユーザー登録</a>
    <a href="coach_login.jsp">コーチ専用ログイン</a>
    </div>
</body>
</html>
