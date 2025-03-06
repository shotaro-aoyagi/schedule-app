package servlet;

import java.io.IOException;

import org.mindrot.jbcrypt.BCrypt;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/CoachLoginServlet")
public class CoachLoginServlet extends HttpServlet {
	// 事前に生成したハッシュ化済みパスワード
	private static final String COACH_PASSWORD_HASH = BCrypt.hashpw("coach123SuperSecretKey123", BCrypt.gensalt());
  
    private static final String PEPPER = "SuperSecretKey123"; // ペッパー（秘密のキー）

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // ユーザー入力のパスワードを取得
        String inputPassword = request.getParameter("coachPassword");

        // ペッパーを適用
        String pepperedPassword = inputPassword + PEPPER;

        // **ペッパー適用後のパスワードをハッシュと比較**
        boolean isPasswordCorrect = BCrypt.checkpw(pepperedPassword, COACH_PASSWORD_HASH);

        if (isPasswordCorrect) {
            HttpSession session = request.getSession();
            session.setAttribute("isCoach", true);
            response.sendRedirect("coach_home.jsp");
        } else {
            request.setAttribute("error", "パスワードが間違っています");
            request.getRequestDispatcher("coach_login.jsp").forward(request, response);
        }
    }

}
