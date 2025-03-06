package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Attendance;
import utils.DBManager;

public class HomeDAO {
	
    public static List<Attendance> getThisWeekAttendance(int userId) {
    	List<Attendance> list = new ArrayList<>();
        String sql = "SELECT user_id, date, status FROM attendance " +
                     "WHERE user_id = ? " +
                     "AND date >= date_trunc('week', CURRENT_DATE) " +
                     "AND date < date_trunc('week', CURRENT_DATE) + INTERVAL '7 days' " +
                     "ORDER BY date ASC";

        try (Connection conn = DBManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Attendance att = new Attendance();
                att.setUserId(rs.getInt("user_id"));
                att.setDate(rs.getDate("date"));
                att.setStatus(rs.getString("status"));
                list.add(att);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
