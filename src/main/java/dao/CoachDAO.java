package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Attendance;
import utils.DBManager;

public class CoachDAO {
	
    // 📅 特定の日の出席データを取得するメソッド
    public static List<Attendance> getAttendanceByDate(Date selectedDate) {
        List<Attendance> list = new ArrayList<>();
        String sql = "SELECT u.name, u.grade, a.date, a.status FROM attendance a "
                   + "JOIN users u ON a.user_id = u.id WHERE a.date = ? "
                   + "ORDER BY u.grade ASC, u.name ASC";

        try (Connection conn = DBManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, selectedDate);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Attendance att = new Attendance();
                att.setName(rs.getString("name"));
                att.setGrade(rs.getInt("grade"));
                att.setDate(rs.getDate("date"));
                att.setStatus(rs.getString("status"));
                list.add(att);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // 全ユーザーの出席データを取得（学年順にソート）
    public static List<Attendance> getAllAttendance() {
        List<Attendance> list = new ArrayList<>();
        String sql = "SELECT u.name, u.grade, a.date, a.status FROM attendance a "
                   + "JOIN users u ON a.user_id = u.id ORDER BY u.grade ASC, u.name ASC, a.date ASC";

        try (Connection conn = DBManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Attendance att = new Attendance();
                att.setName(rs.getString("name"));
                att.setGrade(rs.getInt("grade"));
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
