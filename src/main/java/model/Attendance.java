package model;

import java.sql.Date;

public class Attendance {
    private int id;       // 出席記録のID
    private int userId;   // ユーザーID（外部キー）
    private Date date;    // 出席日
    private String status; // 出席状況（"出席" または "欠席"）

    private String name;
    private int grade;
    
    // デフォルトコンストラクタ
    public Attendance() {}

    // ** 通常の出席登録用コンストラクタ **
    public Attendance(int userId, Date date, String status) {
        this.userId = userId;
        this.date = date;
        this.status = status;
    }

    // ** 既存の出席データ取得用コンストラクタ **
    public Attendance(int id, int userId, Date date, String status) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.status = status;
    }

    public Attendance(String name, int grade, Date date, String status) {
        this.name = name;
        this.grade = grade;
        this.date = date;
        this.status = status;
    }
    
    // ** ゲッター & セッター **
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getGrade() { return grade; }
    public void setGrade(int grade) { this.grade = grade; }
}
