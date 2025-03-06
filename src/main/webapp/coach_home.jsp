<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    // セッションオブジェクトを取得
    HttpSession sessionObj = request.getSession(false);

    // セッションが存在しない、または `isCoach` フラグが `true` でない場合はログインページへリダイレクト
    if (sessionObj == null || sessionObj.getAttribute("isCoach") == null || !(Boolean) sessionObj.getAttribute("isCoach")) {
        response.sendRedirect("coach_login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ホーム</title>
<link rel="stylesheet" type="text/css" href="css/coach_home.css">
</head>
<body>
	<div class="coach-container">
	<h1>コーチ管理画面</h1>
<ul>
    <li><a href="CoachServlet" class="button button-green">全ユーザーの出席状況を見る</a></li>
    <li><a href="logout" class="button button-red">ログアウト</a></li>
</ul>
	</div>
</body>
</html>