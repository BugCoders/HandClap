package se.appmanage;

import java.util.List;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class PPAndroidSpider implements PageProcessor {
    private Site site = Site.me().setCycleRetryTimes(3).setSleepTime(100);
    private static int count = 0;

    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public void process(Page page) {
        String head = "https://www.25pp.com";
        String curUrl = page.getUrl().toString();
        if (page.getUrl().regex("https://www.25pp.com").match()) {
            page.addTargetRequest("https://www.25pp.com/android");
            page.addTargetRequest("https://www.25pp.com/android/game");    
        } 
        if (page.getUrl().regex("https://www.25pp.com/android").match() || page.getUrl().regex("https://www.25pp.com/android/game").match()) {
            List<String> tmp = page.getHtml().xpath("//*a[@data-stat-pos=\"cateList\"]/@href").all();
            for (String s : tmp) {
                for (int i = 1; i <= 20; i++) {
                    page.addTargetRequest(head + s + i);
                }
            }
        } 
        if (page.getUrl().regex("https://www.25pp.com/android/soft/fenlei/[0-9]{4}/[0-9]+").match()) {
            List<String> tmp = page.getHtml().xpath("//*a[@class=\"app-icon\"]/@href").all();
            for (String s : tmp) {
                page.addTargetRequest(head + s);
            }
        }
        if (page.getUrl().regex("https://www.25pp.com/android/comment_[0-9]+").match()) {
            String originAndroidID = curUrl.substring(curUrl.indexOf('_'));     
            String appName = page.getHtml().xpath("//*a[@class=\"btn-install large-btn\"]/@appname").get().trim();   
            List<String> commentsList1 = page.getHtml().xpath("//*div[@class=\"comment-item\"]/p/span/text()").all();
            List<String> commentsList2 = page.getHtml().xpath("//*div[@class=\"comment-item\"]/p/text()").all();          
            /*System.out.println("name:" + appName);
            System.out.println("comment:" + commentsList1);
            System.out.println("comment2:" + commentsList2);*/
            if (commentsList1 != null && commentsList2 != null) {
                int len = commentsList1.size();
                if (len > 0 && len % 2 == 0) {
                    for (int i = 0; i < len; i += 2) {
                        CommentEntity newComment = new CommentEntity();
                        newComment.setOriginAndroidID(originAndroidID);
                        newComment.setAppName(appName);
                        newComment.setUserID(commentsList1.get(i));
                        newComment.setDate(commentsList1.get(i + 1));
                        newComment.setContent(commentsList2.get(i + 1));
                        DBMethods.insertComment(newComment);
                    }
                }
            }
        } else if (page.getUrl().regex("https://www.25pp.com/android/detail_[0-9]+").match()) {
            page.addTargetRequest(curUrl.replaceAll("detail", "comment"));
            String originAndroidID = curUrl.substring(curUrl.indexOf('_'));
            String originPage = curUrl;
            String appName = page.getHtml().xpath("//*a[@class=\"btn-install large-btn\"]/@appname").get().trim();  
            String downloadLink_android = page.getHtml().xpath("//*a[@class=\"btn-install large-btn\"]/@appdownurl").get();
            int dlNum = SpiderDataFormatter
                    .formatDownloadNumbers(page.getHtml().xpath("//*div[@class=\"app-downs\"]/text()").get());
            float score_android = SpiderDataFormatter
                    .formatScore(page.getHtml().xpath("//*div[@class=\"app-score\"]/@title").get());
            String info = page.getHtml().xpath("//*div[@class=\"app-detail-intro expand-panel\"]/tidyText()").get();
            String category = SpiderDataFormatter.formatAndroidCategory(
                    page.getHtml().xpath("//*a[@data-stat-pos=\"appTag\"]/text()").get()); 

            String screenShot = SpiderDataFormatter.formatScreenShot(
                    page.getHtml().xpath("//*ul[@class=\"gallery clearfix\"]/li/img/@src").all());
            String iconLink = page.getHtml().xpath("//*div[@class=\"detail-header clearfix\"]/div/img/@src").get();
            List<String> tmp = page.getHtml().xpath("//*span[@class=\"ellipsis\"]//strong/text()").all();            
            String osPerm = SpiderDataFormatter.formatOsPerm(tmp.get(3));
            String changeDate = tmp.get(0);
            String fileSize = tmp.get(1);
            String versionNumber = tmp.get(2);
            //String author = page.getHtml().xpath("//*span[@class=\"dev-sites\"]/text()").get();
    
             /*System.out.println("结果"); System.out.println("name:" + appName);
             System.out.println("icon:" + iconLink);
             System.out.println("dlnum:" + dlNum);
             System.out.println("score:" + score_android); System.out.println("category:" +
             category); System.out.println("fileSize:" + fileSize);
             System.out.println("info:" + info); System.out.println("osPerm:" + osPerm);
             System.out.println("changeDate:" + changeDate);
             System.out.println("versionNumber:" + versionNumber);
             System.out.println("downlink:" + downloadLink_android);
             
             System.out.println("ScreenShot:" + screenShot);*/
             //System.out.println("author:" + author);
             
            AppEntity newApp = new AppEntity();
            newApp.setOriginPage_android(originPage);
            newApp.setOriginAndroidID(originAndroidID);
            //newApp.setAuthor(author);
            newApp.setCategory(category);
            newApp.setChangeDate_android(changeDate);
            newApp.setFileSize_android(fileSize);
            newApp.setIconLink(iconLink);
            newApp.setInfo_android(info);
            newApp.setName(appName);
            newApp.setOsPerm_android(osPerm);
            newApp.setScore_android(score_android);
            newApp.setVersionNumber_android(versionNumber);
            newApp.setDownloadLink_android(downloadLink_android);
            newApp.setDownloadNumber_android(dlNum);
            newApp.setScreenShot_android(screenShot);
            newApp.setIsAndroid(1);
            DBMethods.insertAndroidApp(newApp);
            count++;
        }
    }

    public static void runSpider() {
        long startTime, endTime;
        System.out.println("开始抓取...");
        startTime = System.currentTimeMillis();
        Spider.create(new PPAndroidSpider()).addUrl("https://www.25pp.com").thread(5).run();
        endTime = System.currentTimeMillis();
        System.out.println("结束，耗时" + ((endTime - startTime) / 1000) + "秒, 共" + count + "条记录");
    }
    
     public static void main(String[] args) { 
         PPAndroidSpider.runSpider();
         //PPIosSpider.runSpider();
     }
     

}

