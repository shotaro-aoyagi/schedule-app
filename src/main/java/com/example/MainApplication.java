package com.example;

import org.apache.catalina.startup.Tomcat;

public class MainApplication {
	public static void main(String[] args) throws Exception {
        // Tomcatの起動
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080); // HerokuのPORTに合わせる
        tomcat.getConnector(); // コネクタの作成
        
        // アプリケーションのディレクトリを設定
        String webAppDir = "src/main/webapp/";
        tomcat.addWebapp("/", new java.io.File(webAppDir).getAbsolutePath());

        System.out.println("Starting Tomcat...");
        tomcat.start();
        tomcat.getServer().await();
    }
}
