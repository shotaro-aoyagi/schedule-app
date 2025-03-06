package servlet;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.CoachDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Attendance;

@WebServlet("/CoachServlet")
public class CoachServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	// 📅 「今日」の日付を取得
        LocalDate today = LocalDate.now();
        String selectedDateStr = request.getParameter("selectedDate");

        List<Attendance> allAttendanceList;

        if (selectedDateStr != null && !selectedDateStr.isEmpty()) {
            // ユーザーが日付を選択した場合 → 選択した日付のデータのみ取得
            Date selectedDate = Date.valueOf(selectedDateStr);
            allAttendanceList = CoachDAO.getAttendanceByDate(selectedDate);
        } else {
            // 初回アクセス時 or 日付未選択の場合 → 今日の日付のデータを取得
            selectedDateStr = today.toString();
            allAttendanceList = CoachDAO.getAttendanceByDate(Date.valueOf(selectedDateStr));
        }

        // 学年ごとに分類
        Map<Integer, List<Attendance>> gradeMap = new HashMap<>();
        // 出席・欠席の集計
        int totalPresent = 0;
        int totalAbsent = 0;

        for (Attendance att : allAttendanceList) {
            // 学年別リスト作成
            gradeMap.computeIfAbsent(att.getGrade(), k -> new ArrayList<>()).add(att);

            // 出席・欠席のカウント
            if ("出席".equals(att.getStatus())) {
                totalPresent++;
            } else if ("欠席".equals(att.getStatus())) {
                totalAbsent++;
            }
        }

        // JSPにデータを送る
        request.setAttribute("selectedDate", selectedDateStr);
        request.setAttribute("gradeMap", gradeMap);
        request.setAttribute("totalPresent", totalPresent);
        request.setAttribute("totalAbsent", totalAbsent);
        request.getRequestDispatcher("coach_attendance.jsp").forward(request, response);
    }
}
