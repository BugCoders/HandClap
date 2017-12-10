package se.appmanage;

public class CommentEntity {
    private int appID;
    private String originAndroidID;
    private String appName;
    private String userID;
    private String date;
    private String content;
    public String getAppName() {
        return appName;
    }
    public void setAppName(String appName) {
        this.appName = appName;
    }
    public String getUserID() {
        return userID;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getAppID() {
        return appID;
    }
    public void setAppID(int appID) {
        this.appID = appID;
    }
    public String getOriginAndroidID() {
        return originAndroidID;
    }
    public void setOriginAndroidID(String originAndroidID) {
        this.originAndroidID = originAndroidID;
    }

    
}
