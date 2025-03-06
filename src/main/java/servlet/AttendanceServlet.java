package servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import dao.AttendanceDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Attendance;
import model.User;

@WebServlet("/attendance")
public class AttendanceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        // セッションからログインユーザーを取得
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");

        // ログインしていない場合はログイン画面へリダイレクト
        if (loginUser == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        int userId = loginUser.getId();
        String dateStr = request.getParameter("date");
        String status = request.getParameter("status");

        if (dateStr == null || status == null) {
            request.setAttribute("error", "日付と出席状況を選択してください");
            request.getRequestDispatcher("/WEB-INF/attendance.jsp").forward(request, response);
            return;
        }

        // 文字列の日付を `java.sql.Date` に変換
        Date sqlDate = Date.valueOf(dateStr);

        // 出席データを追加
        boolean isAdded = AttendanceDAO.addAttendance(userId, sqlDate, status);

        if (!isAdded) {
            request.setAttribute("error", "出席登録に失敗しました");
            request.getRequestDispatcher("/WEB-INF/attendance.jsp").forward(request, response);
            return;
        }

        // 成功時に出席一覧を取得して `attendance.jsp` にフォワード
        List<Attendance> attendanceList = AttendanceDAO.getAttendanceByUserId(userId);
        request.setAttribute("attendanceList", attendanceList);
        request.getRequestDispatcher("/WEB-INF/attendance.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        int userId = loginUser.getId();
        List<Attendance> attendanceList = AttendanceDAO.getAttendanceByUserId(userId);
        request.setAttribute("attendanceList", attendanceList);

        request.getRequestDispatcher("/WEB-INF/attendance.jsp").forward(request, response);
    }
}
