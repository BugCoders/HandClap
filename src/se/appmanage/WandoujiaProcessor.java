package se.appmanage;

import java.util.List;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class WandoujiaProcessor implements PageProcessor {
    private Site site = Site.me().setCycleRetryTimes(3).setSleepTime(100);
    private static int count = 0;

    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public void process(Page page) {
        String curUrl = page.getUrl().toString();
        if (page.getUrl().regex("http://www.wandoujia.com/apps").match()) {
            List<String> tmp = page.getHtml().xpath("//*a[@class=\"cate-link\"]/@href").all();
            for (String s : tmp) {
                for (int i = 1; i < 42; i++) {
                    page.addTargetRequest(s + "/" + i);
                }
            }
        } else if (page.getUrl().regex("http://www.wandoujia.com/category/[0-9]{4}/[0-9]+").match()) {
            List<String> tmp = page.getHtml().xpath("//div[@class=\"app-desc\"]/h2/a/@href").all();
            page.addTargetRequests(tmp);
            for (String s : tmp) {
                for (int i = 1; i <= 10; i++) {
                    page.addTargetRequest(s + "/comment" + i);
                }
            }
        }
        if (page.getUrl().regex("http://www.wandoujia.com/apps/[a-zA-Z0-9._]+/comment[0-9]+").match()) {
            String appID = curUrl.substring(30, curUrl.lastIndexOf("comment") - 1);
            List<String> commentsList = page.getHtml().xpath("//*li[@class=\"normal-li\"]/p/span/text()").all();
            if (commentsList != null) {
                int len = commentsList.size();
                if (len > 0 && len % 3 == 0) {
                    for (int i = 0; i < len; i += 3) {
                        CommentEntity newComment = new CommentEntity();
                        newComment.setAppID(appID);
                        newComment.setUserID(commentsList.get(i));
                        newComment.setDate(commentsList.get(i + 1));
                        newComment.setContent(commentsList.get(i + 2));
                        DBMethods.insertComment(newComment);
                    }
                }
            }
        } else if (page.getUrl().regex("http://www.wandoujia.com/apps/[a-zA-Z0-9._]+").match()) {
            String downloadLink = curUrl + "/binding?source=web-inner-referral-binded";
            String appID = curUrl.substring(30);
            String name = page.getHtml().xpath("//*body/@data-title").get();
            int dlNum = WandoujiaDataFormatter
                    .formatDownloadNumbers(page.getHtml().xpath("//*i[@itemprop=\"interactionCount\"]/text()").get());
            float score = WandoujiaDataFormatter
                    .formatScore(page.getHtml().xpath("//*span[@class=\"item love\"]/i/text()").get());
            String category = WandoujiaDataFormatter.formatCategory(
                    page.getHtml().xpath("//*a[@itemprop=\"SoftwareApplicationCategory\"]/text()").get());
            String fileSize = page.getHtml().xpath("//*meta[@itemprop=\"fileSize\"]/@content").get();
            String iconLink = page.getHtml().xpath("//*div[@class=\"app-icon\"]/img/@src").get();
            String info = WandoujiaDataFormatter
                    .formatInfo(page.getHtml().xpath("//*div[@itemprop=\"description\"]/tidyText()").get());
            String osPerm = WandoujiaDataFormatter
                    .formatOsPerm(page.getHtml().xpath("//*dd[@itemprop=\"operatingSystems\"]/text()").get());
            String changeDate = page.getHtml().xpath("//*time[@itemprop=\"datePublished\"]/@datetime").get();
            String versionNumber = page.getHtml().xpath("//*div[@class=\"download-wp\"]/a/@data-app-vname").get();
            String author = page.getHtml().xpath("//*span[@class=\"dev-sites\"]/text()").get();
            
             /*System.out.println("抓取结果："); System.out.println("name:" + name);
             System.out.println("dlnum:" + dlNum);
             System.out.println("score:" + score); System.out.println("category:" +
             category); System.out.println("fileSize:" + fileSize);
             System.out.println("info:" + info); System.out.println("osPerm:" + osPerm);
             System.out.println("changeDate:" + changeDate);
             System.out.println("versionNumber:" + versionNumber);
             System.out.println("author:" + author);*/
             
            AppEntity newApp = new AppEntity();
            newApp.setAppID(appID);
            newApp.setAuthor(author);
            newApp.setCategory(category);
            newApp.setChangeDate(changeDate);
            newApp.setFileSize(fileSize);
            newApp.setIconLink(iconLink);
            newApp.setInfo(info);
            newApp.setName(name);
            newApp.setOsPerm(osPerm);
            newApp.setScore(score);
            newApp.setVersionNumber(versionNumber);
            newApp.setDownloadLink(downloadLink);
            newApp.setDownloadNumber(dlNum);
            DBMethods.insertApp(newApp);
            count++;
        }
    }

    public static void runSpider() {
        long startTime, endTime;
        System.out.println("开始抓取...");
        startTime = System.currentTimeMillis();
        Spider.create(new WandoujiaProcessor()).addUrl("http://www.wandoujia.com/apps").thread(5).run();
        endTime = System.currentTimeMillis();
        System.out.println("抓去结束，耗时" + ((endTime - startTime) / 1000) + "秒，共抓取" + count + "条记录");
    }
    
     /*public static void main(String[] args) { 
         WandoujiaProcessor.runSpider();
     }*/
     

}
