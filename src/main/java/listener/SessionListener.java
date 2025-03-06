package listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener  // 🔹 `web.xml` に設定しなくても Tomcat に認識される！
public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setMaxInactiveInterval(30 * 60); // 🔹 30分（秒単位）
        System.out.println("🟢 セッションが作成されました。タイムアウト: 30分");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("🔴 セッションが破棄されました。");
    }
}
