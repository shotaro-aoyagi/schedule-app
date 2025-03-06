package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Attendance;

public class AttendanceDAO {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/attendance_db";
    private static final String DB_USER = "admin";
    private static final String DB_PASSWORD = "password";
    
    public static boolean addAttendance(int userId, Date date, String status) {
        String sql = "INSERT INTO attendance (user_id, date, status) "
                + "VALUES (?, ?, ?) "
                + "ON CONFLICT (user_id, date) DO UPDATE SET status = EXCLUDED.status";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setDate(2, date);
            pstmt.setString(3, status);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Attendance> getAttendanceByUserId(int userId) {
        List<Attendance> list = new ArrayList<>();
        String sql = "SELECT id, user_id, date, status FROM attendance WHERE user_id = ? ORDER BY date DESC";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Attendance att = new Attendance(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getDate("date"),
                    rs.getString("status")
                );
                list.add(att);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
