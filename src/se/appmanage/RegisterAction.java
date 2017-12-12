package se.appmanage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.opensymphony.xwork2.ActionSupport;

public class RegisterAction extends ActionSupport{
    private static final long serialVersionUID = 6024025372968775526L;
    private String username1;
    private String password1;
    private int type1 = 1;
    
    public String getUsername1() {
        return username1;
    }

    public void setUsername1(String username1) {
        this.username1 = username1;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public int getType1() {
        return type1;
    }

    public void setType1(int type1) {
        this.type1 = type1;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String Register() {
        Connection conn = null;
        String ret = null;
        try {
            /*
             * Class.forName("com.mysql.jdbc.Driver"); conn = DriverManager.getConnection(
             * "jdbc:mysql://localhost:3306/appdb?characterEncoding=utf8&useSSL=true",
             * "root", "3.1415926");
             */
            conn = DBConnection.getConnection();
            String sql = "select * from user where username = ?";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, username1);
            // ptmt.setString(1, queryName);
            ResultSet rs = ptmt.executeQuery();
            if (rs.next()) {            
                ret = "exist";
            }
            else {
            sql = "insert into user(username,password,type) values(?,?,?)";
            ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, username1);
            ptmt.setString(2, MD5Util.getMD5(password1));
            ptmt.setInt(3, type1);
            // ptmt.setString(1, queryName);
            ptmt.execute();
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

}
