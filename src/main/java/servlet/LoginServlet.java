package servlet;

import java.io.IOException;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // 入力チェック
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            request.setAttribute("error", "メールアドレスとパスワードを入力してください");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        // 認証処理
        User user = UserDAO.checkLogin(email, password);

        if (user != null) {
            // セッション作成 & ユーザー情報を保存
            HttpSession session = request.getSession();
            session.setAttribute("loginUser", user);

            // ホーム画面へリダイレクト
            response.sendRedirect("home");
        } else {
            request.setAttribute("error", "メールアドレスまたはパスワードが間違っています");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
