<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>新規ユーザー登録</title>
    <link rel="stylesheet" type="text/css" href="css/register.css">
</head>
<body>
	<div class="form-container">
    <h1>新規ユーザー登録</h1>

    <!-- エラーメッセージがある場合表示 -->
    <% if (request.getAttribute("error") != null) { %>
        <p style="color: red;"><%= request.getAttribute("error") %></p>
    <% } %>

    <form action="register" method="post">
    	<div class="form-group">
        <label>名前: <input type="text" name="name" required></label><br>
        </div>
        
        <div class="form-group">
        <label>Email: <input type="email" name="email" required></label><br>
        </div>
        
        <div class="form-group">
        <label>パスワード: <input type="password" name="password" required></label><br>
        </div>
        
        <div class="form-group">
        <label>学年:
            <select name="grade">
                <option value="5">5年</option>
                <option value="6">6年</option>
            </select>
        </label><br>
        </div>
        
        <button type="submit" class="button button-green">登録</button>
    </form>
    
    <p><a href="index.jsp" class="return-link">ログイン画面へ戻る</a></p>
    </div>
</body>
</html>
