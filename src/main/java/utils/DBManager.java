package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    private static final String URL = "jdbc:postgresql://localhost:5432/attendance_db";
    private static final String USER = "admin";
    private static final String PASSWORD = "password";

    static {
        try {
            Class.forName("org.postgresql.Driver"); // JDBCドライバーのロード
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("PostgreSQL JDBC Driver not found!", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
