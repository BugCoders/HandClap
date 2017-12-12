package se.appmanage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport{

    /**
     * 
     */
    private static final long serialVersionUID = 6024025372968775526L;
    private String username;
    private String password;
    private int type = 2;
    private String appName;
    private String appID;
    public String getAppID() {
        return appID;
    }
    public void setAppID(String appID) {
        this.appID = appID;
    }
    private String content;
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getAppName() {
        return appName;
    }
    public void setAppName(String appName) {
        this.appName = appName;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public String Login() {
        Connection conn = null;
        String ret = null;
        try {
            /*
             * Class.forName("com.mysql.jdbc.Driver"); conn = DriverManager.getConnection(
             * "jdbc:mysql://localhost:3306/appdb?characterEncoding=utf8&useSSL=true",
             * "root", "3.1415926");
             */
            conn = DBConnection.getConnection();
            String sql = "select * from user where username = ? and password = ?";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, username);
            ptmt.setString(2, MD5Util.getMD5(password));
            // ptmt.setString(1, queryName);
            ResultSet rs = ptmt.executeQuery();
            ret = "noresult";
            while (rs.next()) {
                type = rs.getInt("type");
                ret = SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
            ret = "error";
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return ret;
    }
    public  String insertComment() {
        Connection conn = null;
        String ret = null;
        try {
            /*
             * Class.forName("com.mysql.jdbc.Driver"); conn = DriverManager.getConnection(
             * "jdbc:mysql://localhost:3306/appdb?characterEncoding=utf8&useSSL=true",
             * "root", "3.1415926");
             */
            conn = DBConnection.getConnection();
            // ResultSet rs = ptmt.executeQuery();
            String sql = "insert into comment(appname,userid,date,content,appid) values(?,?,?,?,?)";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");  
            ptmt.setString(1, appName);
            ptmt.setString(2, username);
            ptmt.setString(3, df.format(System.currentTimeMillis()).toString());
            ptmt.setString(4, content);
            ptmt.setString(5, appID);
            ptmt.execute();
            ret = SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            ret = "error";
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return ret;
    }

}
