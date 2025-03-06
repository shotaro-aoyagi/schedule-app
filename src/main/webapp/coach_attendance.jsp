<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Map, java.util.List, model.Attendance" %>
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
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>全ユーザーの出席状況</title>
    <link rel="stylesheet" type="text/css" href="css/attendance.css">
</head>
<body>
	<div class="container">
    <h1>全ユーザーの出席状況</h1>

    <!-- 📅 日付選択フォーム -->
    <form action="CoachServlet" method="GET" class="form-container">
        <label for="selectedDate">日付を選択:</label>
        <input type="date" id="selectedDate" name="selectedDate" required>
        <button type="submit" class="button">表示</button>
    </form>

    <hr>

    <!-- 出席者数・欠席者数の集計 -->
    <p class="summary">出席者数: <%= request.getAttribute("totalPresent") %> 人</p>
    <p class="summary">欠席者数: <%= request.getAttribute("totalAbsent") %> 人</p>

    <hr>

    <!-- 学年ごとに出席データを表示 -->
    <%
        Map<Integer, List<Attendance>> gradeMap = (Map<Integer, List<Attendance>>) request.getAttribute("gradeMap");
        if (gradeMap != null) {
            for (Integer grade : gradeMap.keySet()) {
    %>
        <h2>学年: <%= grade %> 年生</h2>
        <table class="table">
            <tr>
                <th>名前</th>
                <th>日付</th>
                <th>出席状況</th>
            </tr>
            <%
                for (Attendance att : gradeMap.get(grade)) {
            %>
                <tr>
                    <td><%= att.getName() %></td>
                    <td><%= att.getDate() %></td>
                    <td><%= att.getStatus() %></td>
                </tr>
            <%
                }
            %>
        </table>
        <hr>
    <%
            }
        }
    %>
    
    <a href="coach_home.jsp" class="button button-green">戻る</a>
    </div>
</body>
</html>
