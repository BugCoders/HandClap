package se.appmanage;

import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;

public class DBMethods {
    public static void createTables() {
        Connection conn = null;
        try {
            /*
             * Class.forName("com.mysql.jdbc.Driver"); conn = DriverManager.getConnection(
             * "jdbc:mysql://localhost:3306/appdb?characterEncoding=utf8&useSSL=true",
             * "root", "3.1415926");
             */
            conn = DBConnection.getConnection();
            // ResultSet rs = ptmt.executeQuery();
            String sql = "CREATE TABLE 'appdb'.'app' (" + "'appid' NVARCHAR(100) NOT NULL,"
                    + "'name' NVARCHAR(100) NOT NULL," + "'info' NVARCHAR(5000) NULL," + "'iconlink' VARCHAR(200) NULL,"
                    + "'filesize' VARCHAR(45) NULL," + "'category' NVARCHAR(45) NULL," + "'changedate' NVARCHAR(45) NULL,"
                    + "'versionnumber' VARCHAR(45) NULL," + "'osperm' NVARCHAR(45) NULL," + "'author' NVARCHAR(100) NULL,"
                    + "'score' FLOAT(3,1) NULL DEFAULT 0," + "'downloadLink' VARCHAR(200) NULL,"
                    + "'downloadnumber' int(20) NULL UNSIGNED DEFAULT 0," + "'visits' INT UNSIGNED NULL DEFAULT 0,"
                    + "PRIMARY KEY ('appid')," + "UNIQUE INDEX 'appid_UNIQUE' ('appid' ASC))" + "ENGINE = InnoDB"
                    + "DEFAULT CHARACTER SET = utf8";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.execute();
            sql = "CREATE TABLE 'appdb'.'comments' (" + "'id' INT UNSIGNED NOT NULL AUTO_INCREMENT,"
                    + "'appid' NVARCHAR(100) NOT NULL," + "'userid' NVARCHAR(100) NOT NULL,"
                    + "'date' NVARCHAR(45) NOT NULL," + "'content' NVARCHAR(1000) NOT NULL)" + "PRIMARY KEY ('id'),"
                    + "UNIQUE INDEX 'idnewd_table_UNIQUE' ('id' ASC))" + "ENGINE = InnoDB"
                    + "DEFAULT CHARACTER SET = utf8";
            ptmt = conn.prepareStatement(sql);
            ptmt.execute();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void insertApp(AppEntity newApp) {
        Connection conn = null;
        try {
            /*
             * Class.forName("com.mysql.jdbc.Driver"); conn = DriverManager.getConnection(
             * "jdbc:mysql://localhost:3306/appdb?characterEncoding=utf8&useSSL=true",
             * "root", "3.1415926");
             */
            conn = DBConnection.getConnection();
            // ResultSet rs = ptmt.executeQuery();
            String sql = "insert into app(appid,name,info,iconlink,filesize,category,"
                    + "changedate,versionnumber,osperm,author,score,downloadLink,downloadnumber,visits) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, newApp.getAppID());
            ptmt.setString(2, newApp.getName());
            ptmt.setString(3, newApp.getInfo());
            ptmt.setString(4, newApp.getIconLink());
            ptmt.setString(5, newApp.getFileSize());
            ptmt.setString(6, newApp.getCategory());
            ptmt.setString(7, newApp.getChangeDate());
            ptmt.setString(8, newApp.getVersionNumber());
            ptmt.setString(9, newApp.getOsPerm());
            ptmt.setString(10, newApp.getAuthor());
            ptmt.setFloat(11, newApp.getScore());
            ptmt.setString(12, newApp.getDownloadLink());
            ptmt.setInt(13, newApp.getDownloadNumber());
            ptmt.setInt(14, newApp.getVisits());
            ptmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void insertComment(CommentEntity newComment) {
        Connection conn = null;
        try {
            /*
             * Class.forName("com.mysql.jdbc.Driver"); conn = DriverManager.getConnection(
             * "jdbc:mysql://localhost:3306/appdb?characterEncoding=utf8&useSSL=true",
             * "root", "3.1415926");
             */
            conn = DBConnection.getConnection();
            // ResultSet rs = ptmt.executeQuery();
            String sql = "insert into comments(appid,userid,date,content) values(?,?,?,?)";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, newComment.getAppID());
            ptmt.setString(2, newComment.getUserID());
            ptmt.setString(3, newComment.getDate());
            ptmt.setString(4, newComment.getContent());
            ptmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
