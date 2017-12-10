package se.appmanage;

import java.util.List;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class PPIosSpider implements PageProcessor {
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
            page.addTargetRequest("https://www.25pp.com/ios");
            page.addTargetRequest("https://www.25pp.com/ios/game");
        }
        if (page.getUrl().regex("https://www.25pp.com/ios").match()
                || page.getUrl().regex("https://www.25pp.com/ios/game").match()) {
            List<String> tmp = page.getHtml().xpath("//*a[@data-stat-pos=\"cateList\"]/@href").all();
            for (String s : tmp) {
                page.addTargetRequest(s.substring(0, s.length() - 1));
            }
        }
        if (page.getUrl().regex("https://www.25pp.com/ios/soft/fenlei/3/[0-9_]+/[0-9]+").match()
                || page.getUrl().regex("https://www.25pp.com/ios/game/fenlei/3/[0-9_]+/[0-9]+").match()) {
            List<String> tmp = page.getHtml().xpath("//*a[@class=\"app-icon\"]/@href").all();
            page.addTargetRequests(tmp);
        } else if (page.getUrl().regex("https://www.25pp.com/ios/soft/fenlei/3/[0-9]+").match()
                || page.getUrl().regex("https://www.25pp.com/ios/game/fenlei/3/[0-9]+").match()) {
            List<String> tmp = page.getHtml().xpath("//*a[@class=\"page-num\"]/text()").all();
            System.out.println("page" + tmp.get(tmp.size() - 1));
            System.out.println(curUrl);
            String max = tmp.get(tmp.size() - 1);
            int maxPages = Integer.valueOf(max);
            for (int i = 1; i <= 1; i++) {
                page.addTargetRequest(curUrl + "_0_2/" + i);
            }
        }

        if (page.getUrl().regex("https://www.25pp.com/ios/detail_[0-9]+").match()) {
            String originPage = curUrl;
            String appName = page.getHtml().xpath("//*div[@class=\"detail-header clearfix\"]/div/img/@title").get().trim();
            // String downloadLink_ios = page.getHtml().xpath("//*a/[@class=\"btn-install
            // large-btn\"]/@appdownurl").get();
            int dlNum = SpiderDataFormatter
                    .formatDownloadNumbers(page.getHtml().xpath("//*div[@class=\"app-downs\"]/text()").get());
            float score_ios = SpiderDataFormatter
                    .formatScore(page.getHtml().xpath("//*div[@class=\"app-score\"]/@title").get());
            String info = page.getHtml().xpath("//*div[@class=\"app-detail-intro expand-panel\"]/tidyText()").get();
            String category = SpiderDataFormatter
                    .formatIosCategory(page.getHtml().xpath("//*div[@class=\"app-tag-list clearfix\"]/a/text()").get());

            String screenShot = SpiderDataFormatter
                    .formatScreenShot(page.getHtml().xpath("//*ul[@class=\"gallery clearfix\"]/li/img/@src").all());
            String iconLink = page.getHtml().xpath("//*div[@class=\"detail-header clearfix\"]/div/img/@src").get();
            List<String> tmp = page.getHtml().xpath("//*span[@class=\"col-1 ellipsis\"]/strong/text()").all();
            List<String> tmp1 = page.getHtml().xpath("//*span[@class=\"col-2 ellipsis\"]/strong/text()").all();
            String osPerm = tmp1.get(1);
            String changeDate = tmp.get(0);
            String fileSize = tmp1.get(0);
            String versionNumber = tmp.get(1);
            // String author =
            // page.getHtml().xpath("//*span[@class=\"dev-sites\"]/text()").get();

            /*System.out.println("结果");
            System.out.println("name:" + appName);
            System.out.println("icon:" + iconLink);
            System.out.println("dlnum:" + dlNum);
            System.out.println("score:" + score_ios);
            System.out.println("category:" + category);
            System.out.println("fileSize:" + fileSize);
            System.out.println("info:" + info);
            System.out.println("osPerm:" + osPerm);
            System.out.println("changeDate:" + changeDate);
            System.out.println("versionNumber:" + versionNumber);

            System.out.println("ScreenShot:" + screenShot);*/
            // System.out.println("author:" + author);

            AppEntity newApp = new AppEntity();
            newApp.setOriginPage_ios(originPage);
            // newApp.setAuthor(author);
            newApp.setCategory(category);
            newApp.setChangeDate_ios(changeDate);
            newApp.setFileSize_ios(fileSize);
            newApp.setIconLink(iconLink);
            newApp.setInfo_ios(info);
            newApp.setName(appName);
            newApp.setOsPerm_ios(osPerm);
            newApp.setScore_ios(score_ios);
            newApp.setVersionNumber_ios(versionNumber);
            newApp.setDownloadNumber_ios(dlNum);
            newApp.setScreenShot_ios(screenShot);
            newApp.setIsIos(1);
            int id = DBMethods.isAndroid(appName,iconLink);
            if (id != -1) {
                newApp.setAppID(id);
                DBMethods.updateApp(newApp);
            } else {
                DBMethods.insertIosApp(newApp);
            }
            count++;
        }
    }

    public static void runSpider() {
        long startTime, endTime;
        System.out.println("开始抓取...");
        startTime = System.currentTimeMillis();
        Spider.create(new PPIosSpider()).addUrl("https://www.25pp.com").thread(5).run();
        endTime = System.currentTimeMillis();
        System.out.println("结束，耗时" + ((endTime - startTime) / 1000) + "秒, 共" + count + "条记录");
    }

    public static void main(String[] args) {
        PPIosSpider.runSpider();
    }

}
