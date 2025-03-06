package dao;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import model.User;
import utils.DBManager;

public class UserDAO {
	
	private static final String DB_URL = "jdbc:postgresql://localhost:5432/attendance_db";
    private static final String DB_USER = "admin";
    private static final String DB_PASSWORD = "password";

    public static List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT id, name, email, grade FROM users ORDER BY id ASC";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                User user = new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getInt("grade")
                );
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    private static final String PEPPER = "固定の秘密キー"; // 環境変数で管理推奨

    /**
     * 新規ユーザー登録
     */
    public static boolean addUser(String name, String email, String password, int grade) {
        String sql = "INSERT INTO users (name, email, password, salt, grade) VALUES (?, ?, ?, ?, ?)";

        // ソルトを生成
        String salt = UUID.randomUUID().toString();
        // パスワードをハッシュ化
        String hashedPassword = hashPassword(password, salt);

        try (Connection conn = DBManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, hashedPassword);
            stmt.setString(4, salt);
            stmt.setInt(5, grade);

            int result = stmt.executeUpdate();

            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * メールアドレスが既に登録されているか確認
     */
    public static boolean isEmailRegistered(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        try (Connection conn = DBManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * パスワードのハッシュ化
     */
    public static String hashPassword(String password, String salt) {
        try {
            String pepperedPassword = password + PEPPER;
            PBEKeySpec spec = new PBEKeySpec(pepperedPassword.toCharArray(), salt.getBytes(), 10000, 256);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("エラー: パスワードハッシュ化失敗", e);
        }
    }
    
    /**
     * ログイン時の認証
     */
    public static User checkLogin(String email, String password) {
        String sql = "SELECT id, name, email, grade, salt, password FROM users WHERE email = ?";

        try (Connection conn = DBManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("password");
                String salt = rs.getString("salt");

                if (hashPassword(password, salt).equals(storedHash)) {
                    return new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getInt("grade")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // 認証失敗
    }

}