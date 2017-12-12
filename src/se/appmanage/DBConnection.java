package se.appmanage;
import java.sql.*;

public class DBConnection {
	private static final String URL = "jdbc:mysql://w.rdc.sae.sina.com.cn:3306/app_handclap?characterEncoding=utf8&useSSL=true";
    private static final String USERNAME = "50j3kln0lm";
    private static final String PASSWORD = "lzjzj24wiwli340k43zi2zhyj1224mkkjwh3xhw0";
    
    //private static final String URL = "jdbc:mysql://localhost:3306/appdb?characterEncoding=utf8&useSSL=true";
    //private static final String USERNAME = "root";
    //private static final String PASSWORD = "123456";
    // private static Connection conn = null;
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}