package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // セッションを取得し、破棄
        HttpSession session = request.getSession(false); // 既存のセッションを取得（新規作成しない）
        if (session != null) {
            session.invalidate(); // セッションを無効化（ログアウト）
        }

        // ログイン画面にリダイレクト
        response.sendRedirect("index.jsp");
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // セッションを無効化（ログアウト処理）
        }
        response.sendRedirect("index.jsp");
    }
}

