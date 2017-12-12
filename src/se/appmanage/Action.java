package se.appmanage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.opensymphony.xwork2.ActionSupport;

public class Action extends ActionSupport {
    /**
     * 
     */
    private static final long serialVersionUID = -4701411111486898232L;

    private static final int MAX_NUMBERS_OF_PAGE = 20;
    private int pageNumber = 1; 
    private List<AppEntity> appList;
    private List<CommentEntity> commentList;
    private String queryName;
    private int queryAppID;
    private AppEntity app;
    private String queryCategory;
    private HashMap<String, String> cateMap;
    private List<String> screenshotList1;
    private List<String> screenshotList2;

    public Action() {
        cateMap = new HashMap<String, String>();
        cateMap.put("1", "游戏 休闲益智");
        cateMap.put("2", "游戏 棋牌桌游");
        cateMap.put("3", "游戏 动作射击");
        cateMap.put("4", "游戏 网络游戏");
        cateMap.put("5", "游戏 跑酷竞速");
        cateMap.put("6", "游戏 经营策略");
        cateMap.put("7", "游戏 体育竞技");
        cateMap.put("8", "游戏 角色扮演");
        cateMap.put("9", "游戏 辅助工具");
        cateMap.put("10", "软件 影音播放");
        cateMap.put("11", "软件 系统工具");
        cateMap.put("12", "软件 通讯社交");
        cateMap.put("13", "软件 摄影美化");
        cateMap.put("14", "软件 阅读学习");
        cateMap.put("15", "软件 生活购物");
        cateMap.put("16", "软件 金融理财");
        cateMap.put("17", "软件 旅游出行");
        cateMap.put("18", "软件,健康运动");
        cateMap.put("19", "软件,办公商务");

    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public List<AppEntity> getAppList() {
        return appList;
    }

    public void setAppList(List<AppEntity> appList) {
        this.appList = appList;
    }

    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    public List<CommentEntity> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentEntity> commentList) {
        this.commentList = commentList;
    }

    public int getQueryAppID() {
        return queryAppID;
    }

    public void setQueryAppID(int queryAppID) {
        this.queryAppID = queryAppID;
    }

    public AppEntity getApp() {
        return app;
    }

    public void setApp(AppEntity app) {
        this.app = app;
    }

    public String getQueryCategory() {
        return queryCategory;
    }

    public void setQueryCategory(String queryCategory) {
        this.queryCategory = queryCategory;
    }

    public HashMap<String, String> getCateMap() {
        return cateMap;
    }

    public void setCateMap(HashMap<String, String> cateMap) {
        this.cateMap = cateMap;
    }

    public List<String> getScreenshotList1() {
        return screenshotList1;
    }

    public void setScreenshotList1(List<String> screenshotList1) {
        this.screenshotList1 = screenshotList1;
    }

    public List<String> getScreenshotList2() {
        return screenshotList2;
    }

    public void setScreenshotList2(List<String> screenshotList2) {
        this.screenshotList2 = screenshotList2;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public static int getMaxNumbersOfPage() {
        return MAX_NUMBERS_OF_PAGE;
    }

    public String queryAppByName() {
        Connection conn = null;
        String ret = null;
        try {
            /*
             * Class.forName("com.mysql.jdbc.Driver"); conn = DriverManager.getConnection(
             * "jdbc:mysql://localhost:3306/appdb?characterEncoding=utf8&useSSL=true",
             * "root", "3.1415926");
             */
            conn = DBConnection.getConnection();
            String sql = "select appid,name,iconLink,downloadnumber_android,downloadnumber_ios,isandroid,isios from app where name like ? or namepinyin like ? order by name asc,appid limit ?,?";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, queryName + "%");
            ptmt.setString(2, queryName + "%");
            ptmt.setInt(3, (pageNumber - 1) * 20);
            ptmt.setInt(4, MAX_NUMBERS_OF_PAGE);
            // ptmt.setString(1, queryName);
            ResultSet rs = ptmt.executeQuery();
            ret = "noresult";
            appList = new ArrayList<AppEntity>();
            while (rs.next()) {
                AppEntity tmp = new AppEntity();
                tmp.setAppID(rs.getInt("appid"));
                tmp.setName(rs.getString("name"));
                // tmp.setInfo(rs.getString("info"));
                tmp.setIconLink(rs.getString("iconlink"));
                tmp.setDownloadNumber_android(rs.getInt("downloadnumber_android"));
                tmp.setDownloadNumber_ios(rs.getInt("downloadnumber_ios"));
                tmp.setIsAndroid(rs.getInt("isandroid"));
                tmp.setIsIos(rs.getInt("isios"));
                /*
                 * tmp.setFileSize(rs.getString("filesize"));
                 * tmp.setCategory(rs.getString("category"));
                 * tmp.setChangeDate(rs.getString("changedate"));
                 * tmp.setVersionNumber(rs.getString("versionnumber"));
                 * tmp.setOsPerm(rs.getString("osperm")); tmp.setAuthor(rs.getString("author"));
                 * tmp.setDownloadLink(rs.getString("downloadLink")); //
                 * tmp.setScore(rs.getFloat("score")); tmp.setVisits(rs.getInt("visits"));
                 */
                appList.add(tmp);
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

    public String queryAppByDownload() {
        Connection conn = null;
        String ret = null;
        try {
            /*
             * Class.forName("com.mysql.jdbc.Driver"); conn = DriverManager.getConnection(
             * "jdbc:mysql://localhost:3306/appdb?characterEncoding=utf8&useSSL=true",
             * "root", "3.1415926");
             */
            conn = DBConnection.getConnection();
            String sql = "select appid,name,iconLink,downloadnumber_android,downloadnumber_ios,isandroid,isios from app order by downloadnumber_android desc,downloadnumber_ios desc,appid limit ?,?";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, (pageNumber - 1) * 20);
            ptmt.setInt(2, MAX_NUMBERS_OF_PAGE);
            ResultSet rs = ptmt.executeQuery();
            ret = "noresult";
            appList = new ArrayList<AppEntity>();
            while (rs.next()) {
                AppEntity tmp = new AppEntity();
                tmp.setAppID(rs.getInt("appid"));
                tmp.setName(rs.getString("name"));
                // tmp.setInfo(rs.getString("info"));
                tmp.setIconLink(rs.getString("iconlink"));
                tmp.setDownloadNumber_android(rs.getInt("downloadnumber_android"));
                tmp.setDownloadNumber_ios(rs.getInt("downloadnumber_ios"));
                tmp.setIsAndroid(rs.getInt("isandroid"));
                tmp.setIsIos(rs.getInt("isios"));
                /*
                 * tmp.setFileSize(rs.getString("filesize"));
                 * tmp.setCategory(rs.getString("category"));
                 * tmp.setChangeDate(rs.getString("changedate"));
                 * tmp.setVersionNumber(rs.getString("versionnumber"));
                 * tmp.setOsPerm(rs.getString("osperm")); tmp.setAuthor(rs.getString("author"));
                 * tmp.setDownloadLink(rs.getString("downloadLink"));
                 * //tmp.setDownloadNumber(rs.getInt("downloadnumber"));
                 * tmp.setScore(rs.getFloat("score")); tmp.setVisits(rs.getInt("visits"));
                 */
                appList.add(tmp);
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

    public String queryAppByCategory() {
        Connection conn = null;
        String ret = null;
        try {
            /*
             * Class.forName("com.mysql.jdbc.Driver"); conn = DriverManager.getConnection(
             * "jdbc:mysql://localhost:3306/appdb?characterEncoding=utf8&useSSL=true",
             * "root", "3.1415926");
             */
            conn = DBConnection.getConnection();
            String sql = "select appid,name,iconLink,downloadnumber_android,downloadnumber_ios,isandroid,isios from app where category = ? order by downloadnumber_android desc,downloadnumber_ios desc,appid limit ?,?";
            PreparedStatement ptmt = conn.prepareStatement(sql);

            ptmt.setString(1, cateMap.get(queryCategory));
            ptmt.setInt(2, (pageNumber - 1) * 20);
            ptmt.setInt(3, MAX_NUMBERS_OF_PAGE);
            ResultSet rs = ptmt.executeQuery();
            ret = "noresult";
            appList = new ArrayList<AppEntity>();
            while (rs.next()) {
                AppEntity tmp = new AppEntity();
                tmp.setAppID(rs.getInt("appid"));
                tmp.setName(rs.getString("name"));
                // tmp.setInfo(rs.getString("info"));
                tmp.setIconLink(rs.getString("iconlink"));
                tmp.setDownloadNumber_android(rs.getInt("downloadnumber_android"));
                tmp.setDownloadNumber_ios(rs.getInt("downloadnumber_ios"));
                tmp.setIsAndroid(rs.getInt("isandroid"));
                tmp.setIsIos(rs.getInt("isios"));
                /*
                 * tmp.setFileSize(rs.getString("filesize"));
                 * tmp.setCategory(rs.getString("category"));
                 * tmp.setChangeDate(rs.getString("changedate"));
                 * tmp.setVersionNumber(rs.getString("versionnumber"));
                 * tmp.setOsPerm(rs.getString("osperm")); tmp.setAuthor(rs.getString("author"));
                 * tmp.setDownloadLink(rs.getString("downloadLink"));
                 * //tmp.setDownloadNumber(rs.getInt("downloadnumber"));
                 * tmp.setScore(rs.getFloat("score")); tmp.setVisits(rs.getInt("visits"));
                 */
                appList.add(tmp);
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

    @SuppressWarnings("unused")
    public String getAppByID() {
        Connection conn = null;
        String ret = null;
        try {
            /*
             * Class.forName("com.mysql.jdbc.Driver"); conn = DriverManager.getConnection(
             * "jdbc:mysql://localhost:3306/appdb?characterEncoding=utf8&useSSL=true",
             * "root", "3.1415926");
             */
            String cateTmp = null;
            conn = DBConnection.getConnection();
            String sql = "select * from app where appid=?";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, queryAppID);
            ResultSet rs = ptmt.executeQuery();
            ret = "noresult";
            String originandroidid = null;
            while (rs.next()) {
                originandroidid = rs.getString("originandroidid");
                app = new AppEntity();
                cateTmp = rs.getString("category");
                app.setAppID(rs.getInt("appid"));
                app.setName(rs.getString("name"));
                if (rs.getString("info_android") != null)
                    app.setInfo_android(rs.getString("info_android").replaceAll("\n", "<br>"));
                if (rs.getString("info_ios") != null)
                    app.setInfo_ios(rs.getString("info_ios").replaceAll("\n", "<br>"));
                app.setIconLink(rs.getString("iconlink"));
                app.setFileSize_android(rs.getString("filesize_android"));
                app.setFileSize_ios(rs.getString("filesize_ios"));
                app.setCategory(rs.getString("category"));
                app.setChangeDate_android(rs.getString("changedate_android"));
                app.setChangeDate_ios(rs.getString("changedate_ios"));
                app.setVersionNumber_android(rs.getString("versionnumber_android"));
                app.setVersionNumber_ios(rs.getString("versionnumber_ios"));
                app.setOsPerm_android(rs.getString("osperm_android"));
                app.setOsPerm_ios(rs.getString("osperm_ios"));
                app.setAuthor(rs.getString("author"));
                app.setDownloadLink_android(rs.getString("downloadlink_android"));
                app.setDownloadLink_ios(rs.getString("downloadlink_ios"));
                app.setDownloadNumber_android(rs.getInt("downloadnumber_android"));
                app.setDownloadNumber_ios(rs.getInt("downloadnumber_ios"));
                app.setScore_android(rs.getFloat("score_android"));
                app.setScore_ios(rs.getFloat("score_ios"));
                app.setVisits(rs.getInt("visits"));
                app.setOriginPage_android(rs.getString("originpage_android"));
                app.setOriginPage_ios(rs.getString("originpage_ios"));
                app.setOriginAndroidID(rs.getString("originandroidid"));
                app.setIsAndroid(rs.getInt("isandroid"));
                app.setIsIos(rs.getInt("isios"));
                app.setScreenShot_android(rs.getString("screenshot_android"));
                app.setScreenShot_ios(rs.getString("screenshot_ios"));
                screenshotList1 = new ArrayList<String>();
                screenshotList2 = new ArrayList<String>();
                String[] tmp = null;
                if (rs.getString("screenshot_android") != null)
                    tmp=rs.getString("screenshot_android").split(" ");
                if (tmp != null) {
                    for (String s : tmp) {
                        screenshotList1.add(s);
                    }
                }
                if (rs.getString("screenshot_ios") != null)
                    tmp=rs.getString("screenshot_ios").split(" ");
                if (tmp != null) {
                    for (String s : tmp) {
                        screenshotList2.add(s);
                    }
                }

                sql = "update app set visits=? where appid=?";
                ptmt = conn.prepareStatement(sql);
                ptmt.setInt(1, rs.getInt("visits") + 1);
                ptmt.setInt(2, queryAppID);
                ptmt.execute();
                ret = SUCCESS;
            }

            sql = "select appid,name,iconLink,downloadnumber_android,downloadnumber_ios,isandroid,isios from app where category = ? order by downloadnumber_android desc,downloadnumber_ios desc,appid limit ?,7";
            ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, cateTmp);
            ptmt.setInt(2, (pageNumber - 1) * 20);
            rs = ptmt.executeQuery();
            ret = "noresult";
            appList = new ArrayList<AppEntity>();
            int i = 0;
            while (rs.next()) {
                if (rs.getInt("appid") == queryAppID || i++ > 6)
                    continue;
                AppEntity tmp = new AppEntity();
                tmp.setAppID(rs.getInt("appid"));
                tmp.setName(rs.getString("name"));
                // tmp.setInfo(rs.getString("info"));
                tmp.setIconLink(rs.getString("iconlink"));
                tmp.setDownloadNumber_android(rs.getInt("downloadnumber_android"));
                tmp.setDownloadNumber_ios(rs.getInt("downloadnumber_ios"));
                tmp.setIsAndroid(rs.getInt("isandroid"));
                tmp.setIsIos(rs.getInt("isios"));
                appList.add(tmp);
                ret = SUCCESS;
            }

            sql = "select userid,date,content from comment where originandroidid=? order by date desc,id limit ?,?";
            ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, originandroidid);
            ptmt.setInt(2, (pageNumber - 1) * 20);
            ptmt.setInt(3, MAX_NUMBERS_OF_PAGE);
            // ptmt.setInt(3, MAX_NUMBERS_OF_PAGE);
            rs = ptmt.executeQuery();
            commentList = new ArrayList<CommentEntity>();
            while (rs.next()) {
                CommentEntity tmp = new CommentEntity();
                tmp.setUserID(rs.getString("userid"));
                tmp.setDate(rs.getString("date"));
                tmp.setContent(rs.getString("content"));
                commentList.add(tmp);
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

    public String queryCommentsByAppID() {
        Connection conn = null;
        String ret = null;
        try {
            /*
             * Class.forName("com.mysql.jdbc.Driver"); conn = DriverManager.getConnection(
             * "jdbc:mysql://localhost:3306/appdb?characterEncoding=utf8&useSSL=true",
             * "root", "3.1415926");
             */
            conn = DBConnection.getConnection();
            String sql = "select userid,date,content from comments where appid=? order by date desc,id limit ?,?";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, queryAppID);
            ptmt.setInt(2, (pageNumber - 1) * 20);
            ptmt.setInt(3, MAX_NUMBERS_OF_PAGE);
            ResultSet rs = ptmt.executeQuery();
            ret = "noresult";
            commentList = new ArrayList<CommentEntity>();
            while (rs.next()) {
                CommentEntity tmp = new CommentEntity();
                tmp.setUserID(rs.getString("userid"));
                tmp.setDate(rs.getString("date"));
                tmp.setContent(rs.getString("content"));
                commentList.add(tmp);
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

    /*
     * public static void main(String args[]) { Action newA = new Action();
     * newA.setQueryName("QQ"); newA.queryAppByName();
     * 
     * List<AppEntity> appList = newA.getAppList();
     * 
     * for (AppEntity s : appList) { System.out.println(s.getName()); }
     * newA.setQueryAppID(appList.get(0).getAppID()); newA.getAppByID(); AppEntity
     * app = newA.getApp(); System.out.println(app.getAppID());
     * System.out.println(app.getName()); System.out.println(app.getInfo());
     * System.out.println(app.getCategory());
     * System.out.println(app.getVersionNumber());
     * System.out.println(app.getFileSize()); System.out.println(app.getIconLink());
     * System.out.println(app.getAuthor()); System.out.println(app.getChangeDate());
     * System.out.println(app.getDownloadLink());
     * System.out.println(app.getDownloadNumber());
     * System.out.println(app.getOsPerm()); System.out.println(app.getScore());
     * System.out.println(app.getVisits()); newA.queryCommentsByAppID();
     * List<CommentEntity> commentList = newA.getCommentList(); for (CommentEntity s
     * : commentList) { System.out.print(s.getUserID() + " ");
     * System.out.print(s.getDate() + " "); System.out.println(s.getContent()); }
     * 
     * newA.setQueryCategory("通讯社交"); newA.queryAppByCategory(); appList =
     * newA.getAppList(); for (AppEntity s : appList) {
     * System.out.println(s.getName()); } }
     */

}
