package se.appmanage;

import java.util.List;

public class SpiderDataFormatter {
    public static int formatDownloadNumbers(String dlNumRes) {
        if (dlNumRes == null || dlNumRes.equals("")) {
            return -1;
        }
        dlNumRes = dlNumRes.replaceAll(" ", "");
        if(dlNumRes.indexOf("下载")!=-1) dlNumRes = dlNumRes.substring(0,dlNumRes.indexOf("下载"));
        int index1 = dlNumRes.indexOf("亿");
        int index2 = dlNumRes.indexOf("万");
        if (index1 != -1) {
            float tmp = Float.parseFloat(dlNumRes.substring(0, index1));
            return (int) (tmp * 100000000);
        } else if (index2 != -1) {
            float tmp = Float.parseFloat(dlNumRes.substring(0, index2));
            return (int) (tmp * 10000);
        } else {
            float tmp = Float.parseFloat(dlNumRes);
            return (int) tmp;
        }
    }

    public static String formatAndroidCategory(String cateRes) {
        if (cateRes !=null && cateRes.length()!=0) {
            switch (cateRes) {
            case "休闲益智": return "游戏 休闲益智";
            case "扑克棋牌": return "游戏 棋牌桌游";
            case "飞行射击": 
            case "动作冒险": return "游戏 动作射击";
            case "网络游戏": return "游戏 网络游戏";
            case "跑酷竞速": return "游戏 跑酷竞速";
            
            case "经营策略": return "游戏 经营策略";
            case "体育竞技": return "游戏 体育竞技";
            case "角色扮演": return "游戏 角色扮演";
            case "辅助工具": return "游戏 辅助工具";
            
            case "影音播放": return "软件 影音播放";
            case "系统工具": return "软件 系统工具";
            case "通讯社交": return "软件 通讯社交";
            case "摄影图像": 
            case "手机美化": return "软件 摄影美化";
            case "新闻阅读": 
            case "考试学习": return "软件 阅读学习";
            case "网上购物":
            case "生活休闲": 
            case "育儿亲子": return "软件 生活购物";
            case "金融理财": return "软件 金融理财";
            
            case "旅游出行": return "软件 旅游出行";
            case "健康运动": return "软件 健康运动";
            case "办公商务": return "软件 办公商务";
            default:
                return "";
            }
        } else {
            return "";
        }
    }
    public static String formatIosCategory(String cateRes) {
        if (cateRes !=null && cateRes.length()!=0) {
            switch (cateRes) {
            case "儿童教育": 
            case "休闲益智": return "游戏 休闲益智";
            case "棋牌桌游": return "游戏 棋牌桌游"; 
            case "动作射击": return "游戏 动作射击";
            case "赛车竞速": return "游戏 跑酷竞速";
            
            case "策略经营": return "游戏 经营策略";
            case "体育竞技": return "游戏 体育竞技";
            case "角色扮演": return "游戏 角色扮演";
            
            case "影音娱乐": return "软件 影音播放";
            case "系统工具": return "软件 系统工具";
            case "社交通讯": return "软件 通讯社交"; 
            case "摄影美化": return "软件 摄影美化";
            case "阅读学习": return "软件 阅读学习";
            case "生活购物": return "软件 生活购物";
            case "金融理财": return "软件 金融理财";
            
            case "出行导航": return "软件 旅游出行";
            case "运动健康": return "软件 健康运动";
            case "商务办公": return "软件 办公商务";
            default: return "";
            }
        } else {
            return "";
        }
    }

    public static float formatScore(String scoreRes) {
        if (scoreRes == null || scoreRes.equals("") || scoreRes.equals("暂无")) {
            return 0;
        } else {
            if(scoreRes.indexOf("分")!=-1) {
                float tmp = Float.parseFloat(scoreRes.substring(0, scoreRes.indexOf("分")));
                return tmp;
            }else {
                float tmp = Float.parseFloat(scoreRes.substring(0, scoreRes.length() - 1));
                return tmp / 20;
            }
            
        }
    }
    public static String formatChangeDate(String date) {
        if (date == null || date.equals("") || date.equals("暂无")) {
            return "";
        } else {
            date = date.replaceAll("年", "-");
            date = date.replaceAll("月", "-");
            date = date.replaceAll("日", "");
            return date;
        }
    }

    public static String formatOsPerm(String osPermRes) {
        if (osPermRes == null || osPermRes.equals("")) {
            return "";
        } else {
            return "Android " + osPermRes +" 以上";
        }
    }
    public static String formatScreenShot(List<String> screenShot) {
        if (screenShot == null || screenShot.size()==0) {
            return "";
        } else {
            StringBuilder builder = new StringBuilder();
            for(String s : screenShot) {
                builder.append(s+" ");
            }
            String tmp = builder.toString();
            tmp = tmp.substring(0,tmp.length()-1);
            return tmp;
        }
    }
    public static String formatInfo(String infoRes) {
        if (infoRes == null || infoRes.equals("")) {
            return "";
        } else {
            if(infoRes.substring(0, 1).equals("\n")) {
                return infoRes.substring(1);
            }
            else {
                return infoRes;
            }
        }
    }
}
