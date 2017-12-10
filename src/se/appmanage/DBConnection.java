package se.appmanage;
import java.sql.*;

public class DBConnection {
	private static final String URL = "jdbc:mysql://localhost:3306/appdb?characterEncoding=utf8&useSSL=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "3.1415926";
    
    //private static final String URL = "jdbc:mysql://localhost:3306/appdb?characterEncoding=utf8&useSSL=true";
    //private static final String USERNAME = "root";
    //private static final String PASSWORD = "";
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