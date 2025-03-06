package model;

public class User {
    private int id;
    private String name;
    private String email;
    private int grade;

    public User(int id, String name, String email, int grade) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.grade = grade;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public int getGrade() { return grade; }

    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "', email='" + email + "', grade=" + grade + "}";
    }
}
