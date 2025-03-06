package servlet;

import java.io.IOException;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String gradeStr = request.getParameter("grade");

        if (name == null || name.isEmpty() ||
            email == null || email.isEmpty() ||
            password == null || password.isEmpty() ||
            gradeStr == null || gradeStr.isEmpty()) {
            System.out.println("[ERROR] 入力値が不足しています");
            request.setAttribute("error", "すべての項目を入力してください。");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        int grade;
        try {
            grade = Integer.parseInt(gradeStr);
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 学年の形式が正しくありません: " + gradeStr);
            request.setAttribute("error", "学年の入力が正しくありません。");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // 許可されている grade の値を確認
        if (grade != 5 && grade != 6) {
            System.out.println("[ERROR] 許可されていない学年: " + grade);
            request.setAttribute("error", "学年は 5 または 6 のみ選択できます。");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        if (UserDAO.isEmailRegistered(email)) {
            System.out.println("[ERROR] メールアドレスが既に登録済み: " + email);
            request.setAttribute("error", "このメールアドレスは既に登録されています。");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        boolean isAdded = UserDAO.addUser(name, email, password, grade);

        if (isAdded) {
            response.sendRedirect("users.jsp");
        } else {
            request.setAttribute("error", "ユーザー登録に失敗しました。");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
        
    }
}
