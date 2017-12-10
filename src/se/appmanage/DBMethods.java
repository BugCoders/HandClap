package se.appmanage;

import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.ResultSet;

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
                    + "'filesize' VARCHAR(45) NULL," + "'category' NVARCHAR(45) NULL,"
                    + "'changedate' NVARCHAR(45) NULL," + "'versionnumber' VARCHAR(45) NULL,"
                    + "'osperm' NVARCHAR(45) NULL," + "'author' NVARCHAR(100) NULL,"
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

    public static int isAndroid(String appName, String iconUrl) {
        Connection conn = null;
        try {
            /*
             * Class.forName("com.mysql.jdbc.Driver"); conn = DriverManager.getConnection(
             * "jdbc:mysql://localhost:3306/appdb?characterEncoding=utf8&useSSL=true",
             * "root", "3.1415926");
             */
            conn = DBConnection.getConnection();
            String sql = "select appid,iconlink,isios,isandroid from app where name = ?";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, appName);
            ResultSet rs = ptmt.executeQuery();
            CompareIcon.downloadPicture(iconUrl, "1");
            double max = 0;
            int id = -1;
            // int flag =1;
            while (rs.next()) {
                if (rs.getInt("isandroid") == 1 && rs.getInt("isios") == 0) {
                    CompareIcon.downloadPicture(rs.getString("iconlink"), "2");
                    // CompareIcon.convertPngToJpg("2.png");
                    double similar = CompareIcon.compareImage("1.jpg", "2.png") ;
                    if (similar > max) {
                        max = similar;
                        id = rs.getInt("appid");
                        // flag = rs.getInt("isios");
                    }
                }
            }
            if (max > 0.5)
                return id;
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
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

    public static void insertAndroidApp(AppEntity newApp) {
        Connection conn = null;
        try {
            /*
             * Class.forName("com.mysql.jdbc.Driver"); conn = DriverManager.getConnection(
             * "jdbc:mysql://localhost:3306/appdb?characterEncoding=utf8&useSSL=true",
             * "root", "3.1415926");
             */
            conn = DBConnection.getConnection();
            // ResultSet rs = ptmt.executeQuery();
            String sql = "insert into app(originpage_android,name,info_android,iconlink,filesize_android,category,"
                    + "changedate_android,versionnumber_android,osperm_android,screenshot_android,score_android,downloadLink_android,downloadnumber_android,isandroid,originandroidid,author) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, newApp.getOriginPage_android());
            ptmt.setString(2, newApp.getName());
            ptmt.setString(3, newApp.getInfo_android());
            ptmt.setString(4, newApp.getIconLink());
            ptmt.setString(5, newApp.getFileSize_android());
            ptmt.setString(6, newApp.getCategory());
            ptmt.setString(7, newApp.getChangeDate_android());
            ptmt.setString(8, newApp.getVersionNumber_android());
            ptmt.setString(9, newApp.getOsPerm_android());
            ptmt.setString(10, newApp.getScreenShot_android());
            ptmt.setFloat(11, newApp.getScore_android());
            ptmt.setString(12, newApp.getDownloadLink_android());
            ptmt.setInt(13, newApp.getDownloadNumber_android());
            ptmt.setInt(14, newApp.getIsAndroid());
            ptmt.setString(15, newApp.getOriginAndroidID());
            ptmt.setString(16, newApp.getAuthor());
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

    public static void insertIosApp(AppEntity newApp) {
        Connection conn = null;
        try {
            /*
             * Class.forName("com.mysql.jdbc.Driver"); conn = DriverManager.getConnection(
             * "jdbc:mysql://localhost:3306/appdb?characterEncoding=utf8&useSSL=true",
             * "root", "3.1415926");
             */
            conn = DBConnection.getConnection();
            // ResultSet rs = ptmt.executeQuery();
            String sql = "insert into app(originpage_ios,name,info_ios,iconlink,filesize_ios,category,"
                    + "changedate_ios,versionnumber_ios,osperm_ios,screenshot_ios,score_ios,downloadLink_ios,downloadnumber_ios,isios) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, newApp.getOriginPage_ios());
            ptmt.setString(2, newApp.getName());
            ptmt.setString(3, newApp.getInfo_ios());
            ptmt.setString(4, newApp.getIconLink());
            ptmt.setString(5, newApp.getFileSize_ios());
            ptmt.setString(6, newApp.getCategory());
            ptmt.setString(7, newApp.getChangeDate_ios());
            ptmt.setString(8, newApp.getVersionNumber_ios());
            ptmt.setString(9, newApp.getOsPerm_ios());
            ptmt.setString(10, newApp.getScreenShot_ios());
            ptmt.setFloat(11, newApp.getScore_ios());
            ptmt.setString(12, newApp.getDownloadLink_ios());
            ptmt.setInt(13, newApp.getDownloadNumber_ios());
            ptmt.setInt(14, newApp.getIsIos());
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

    public static void updateApp(AppEntity newApp) {
        Connection conn = null;
        try {
            /*
             * Class.forName("com.mysql.jdbc.Driver"); conn = DriverManager.getConnection(
             * "jdbc:mysql://localhost:3306/appdb?characterEncoding=utf8&useSSL=true",
             * "root", "3.1415926");
             */
            conn = DBConnection.getConnection();
            // ResultSet rs = ptmt.executeQuery();
            String sql = "update app set originpage_ios=?,filesize_ios=?,"
                    + "changedate_ios=?,versionnumber_ios=?,osperm_ios=?,screenshot_ios=?,score_ios=?,downloadLink_ios=?,downloadnumber_ios=?,isios=?,info_ios=? where appid=?";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, newApp.getOriginPage_ios());
            ptmt.setString(2, newApp.getFileSize_ios());
            ptmt.setString(3, newApp.getChangeDate_ios());
            ptmt.setString(4, newApp.getVersionNumber_ios());
            ptmt.setString(5, newApp.getOsPerm_ios());
            ptmt.setString(6, newApp.getScreenShot_ios());
            ptmt.setFloat(7, newApp.getScore_ios());
            ptmt.setString(8, newApp.getDownloadLink_ios());
            ptmt.setInt(9, newApp.getDownloadNumber_ios());
            ptmt.setInt(10, newApp.getIsIos());
            ptmt.setString(11, newApp.getInfo_ios());
            ptmt.setInt(12, newApp.getAppID());

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
            String sql = "insert into comment(appname,userid,date,content,originandroidid) values(?,?,?,?,?)";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, newComment.getAppName());
            ptmt.setString(2, newComment.getUserID());
            ptmt.setString(3, newComment.getDate());
            ptmt.setString(4, newComment.getContent());
            ptmt.setString(5, newComment.getOriginAndroidID());
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
