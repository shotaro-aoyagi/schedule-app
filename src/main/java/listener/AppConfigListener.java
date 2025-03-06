package listener;

import java.util.EnumSet;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.SessionTrackingMode;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppConfigListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("🟢 アプリケーション起動: セッショントラッキングモードを COOKIE に設定");
        sce.getServletContext().setSessionTrackingModes(EnumSet.of(SessionTrackingMode.COOKIE)); // 🔹 URL書き換えを防ぐ
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("🔴 アプリケーション終了");
    }
}
