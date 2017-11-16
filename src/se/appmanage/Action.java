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
    private String queryAppID;
    private AppEntity app;
    private String queryCategory;
    private HashMap<String,String> cateMap;

    public Action() {
        cateMap = new HashMap<String,String>();
        cateMap.put("1", "游戏,休闲益智");
        cateMap.put("2", "游戏,扑克棋牌");
        cateMap.put("3", "游戏,飞行射击");
        cateMap.put("4", "游戏,网络游戏");
        cateMap.put("5", "游戏,跑酷竞速");
        cateMap.put("6", "游戏,动作冒险");
        cateMap.put("7", "游戏,经营策略");
        cateMap.put("8", "游戏,体育竞技");
        cateMap.put("9", "游戏,角色扮演");
        cateMap.put("10", "游戏,辅助工具");
        cateMap.put("11", "软件,影音播放");
        cateMap.put("12", "软件,系统工具");
        cateMap.put("13", "软件,通讯社交");
        cateMap.put("14", "软件,手机美化");
        cateMap.put("15", "软件,新闻阅读");
        cateMap.put("16", "软件,摄影图像");
        cateMap.put("17", "软件,考试学习");
        cateMap.put("18", "软件,网上购物");
        cateMap.put("19", "软件,金融理财");
        cateMap.put("20", "软件,生活休闲");
        cateMap.put("21", "软件,旅游出行");
        cateMap.put("22", "软件,健康运动");
        cateMap.put("23", "软件,办公商务");
        cateMap.put("24", "软件,育儿亲子");
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

    public String getQueryAppID() {
        return queryAppID;
    }

    public void setQueryAppID(String queryAppID) {
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
            String sql = "select appid,name,iconLink,downloadnumber from app where name like ? order by name asc,appid limit ?,?";
            // String sql = "select appid,name,iconLink from app where FIND_IN_SET(?, name)
            // order by name asc";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, queryName + "%");
            ptmt.setInt(2, (pageNumber - 1) * 20);
            ptmt.setInt(3, MAX_NUMBERS_OF_PAGE);
            // ptmt.setString(1, queryName);
            ResultSet rs = ptmt.executeQuery();
            ret = "noresult";
            appList = new ArrayList<AppEntity>();
            while (rs.next()) {
                AppEntity tmp = new AppEntity();
                tmp.setAppID(rs.getString("appid"));
                tmp.setName(rs.getString("name"));
                // tmp.setInfo(rs.getString("info"));
                tmp.setIconLink(rs.getString("iconlink"));
                tmp.setDownloadNumber(rs.getInt("downloadnumber"));
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
            String sql = "select appid,name,iconLink,downloadnumber from app order by downloadnumber desc,appid limit ?,?";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, (pageNumber - 1) * 20);
            ptmt.setInt(2, MAX_NUMBERS_OF_PAGE);
            ResultSet rs = ptmt.executeQuery();
            ret = "noresult";
            appList = new ArrayList<AppEntity>();
            while (rs.next()) {
                AppEntity tmp = new AppEntity();
                tmp.setAppID(rs.getString("appid"));
                tmp.setName(rs.getString("name"));
                // tmp.setInfo(rs.getString("info"));
                tmp.setIconLink(rs.getString("iconlink"));
                tmp.setDownloadNumber(rs.getInt("downloadnumber"));
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
            String sql = "select appid,name,iconLink,downloadnumber from app where category = ? order by downloadnumber desc,appid limit ?,?";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            
            ptmt.setString(1, cateMap.get(queryCategory));
            ptmt.setInt(2, (pageNumber - 1) * 20);
            ptmt.setInt(3, MAX_NUMBERS_OF_PAGE);
            ResultSet rs = ptmt.executeQuery();
            ret = "noresult";
            appList = new ArrayList<AppEntity>();
            while (rs.next()) {
                AppEntity tmp = new AppEntity();
                tmp.setAppID(rs.getString("appid"));
                tmp.setName(rs.getString("name"));
                // tmp.setInfo(rs.getString("info"));
                tmp.setIconLink(rs.getString("iconlink"));
                tmp.setDownloadNumber(rs.getInt("downloadnumber"));
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

    public String getAppByID() {
        Connection conn = null;
        String ret = null;
        try {
            /*
             * Class.forName("com.mysql.jdbc.Driver"); conn = DriverManager.getConnection(
             * "jdbc:mysql://localhost:3306/appdb?characterEncoding=utf8&useSSL=true",
             * "root", "3.1415926");
             */
            String cateTmp=null;
            conn = DBConnection.getConnection();
            String sql = "select * from app where appid=?";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, queryAppID);
            ResultSet rs = ptmt.executeQuery();
            ret = "noresult";
            while (rs.next()) {
                app = new AppEntity();
                cateTmp = rs.getString("category");
                app.setAppID(rs.getString("appid"));
                app.setName(rs.getString("name"));
                app.setInfo(rs.getString("info").replaceAll("\n", "<br>"));
                app.setIconLink(rs.getString("iconlink"));
                app.setFileSize(rs.getString("filesize"));
                app.setCategory(rs.getString("category"));
                app.setChangeDate(rs.getString("changedate"));
                app.setVersionNumber(rs.getString("versionnumber"));
                app.setOsPerm(rs.getString("osperm"));
                app.setAuthor(rs.getString("author"));
                app.setDownloadLink(rs.getString("downloadlink"));
                app.setDownloadNumber(rs.getInt("downloadnumber"));
                app.setScore(rs.getFloat("score"));
                app.setVisits(rs.getInt("visits"));
                sql = "update app set visits=? where appid=?";
                ptmt = conn.prepareStatement(sql);
                ptmt.setInt(1, rs.getInt("visits") + 1);
                ptmt.setString(2, queryAppID);
                ptmt.execute();
                ret = SUCCESS;
            }
            
            sql = "select appid,name,iconLink,downloadnumber from app where category = ? order by downloadnumber desc,appid limit ?,6";
            ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, cateTmp);
            ptmt.setInt(2, (pageNumber - 1) * 20);
            rs = ptmt.executeQuery();
            ret = "noresult";
            appList = new ArrayList<AppEntity>();
            while (rs.next()) {
                AppEntity tmp = new AppEntity();
                tmp.setAppID(rs.getString("appid"));
                tmp.setName(rs.getString("name"));
                // tmp.setInfo(rs.getString("info"));
                tmp.setIconLink(rs.getString("iconlink"));
                tmp.setDownloadNumber(rs.getInt("downloadnumber"));
                appList.add(tmp);
                ret = SUCCESS;
            }
            
            sql = "select userid,date,content from comments where appid=? order by date desc,id limit ?,?";
            ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, queryAppID);
            ptmt.setInt(2, (pageNumber - 1) * 20);
            ptmt.setInt(3, MAX_NUMBERS_OF_PAGE);
            //ptmt.setInt(3, MAX_NUMBERS_OF_PAGE);
            rs = ptmt.executeQuery();
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
            ptmt.setString(1, queryAppID);
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

    /*public static void main(String args[]) {
        Action newA = new Action();
        newA.setQueryName("QQ");
        newA.queryAppByName();

        List<AppEntity> appList = newA.getAppList();

        for (AppEntity s : appList) {
            System.out.println(s.getName());
        }
        newA.setQueryAppID(appList.get(0).getAppID());
        newA.getAppByID();
        AppEntity app = newA.getApp();
        System.out.println(app.getAppID());
        System.out.println(app.getName());
        System.out.println(app.getInfo());
        System.out.println(app.getCategory());
        System.out.println(app.getVersionNumber());
        System.out.println(app.getFileSize());
        System.out.println(app.getIconLink());
        System.out.println(app.getAuthor());
        System.out.println(app.getChangeDate());
        System.out.println(app.getDownloadLink());
        System.out.println(app.getDownloadNumber());
        System.out.println(app.getOsPerm());
        System.out.println(app.getScore());
        System.out.println(app.getVisits());
        newA.queryCommentsByAppID();
        List<CommentEntity> commentList = newA.getCommentList();
        for (CommentEntity s : commentList) {
            System.out.print(s.getUserID() + " ");
            System.out.print(s.getDate() + " ");
            System.out.println(s.getContent());
        }

        newA.setQueryCategory("通讯社交");
        newA.queryAppByCategory();
        appList = newA.getAppList();
        for (AppEntity s : appList) {
            System.out.println(s.getName());
        }
    }*/

}
