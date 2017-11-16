package se.appmanage;


public class WandoujiaDataFormatter {
    public static int formatDownloadNumbers(String dlNumRes) {
        if (dlNumRes == null || dlNumRes.equals("")) {
            return -1;
        }
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

    public static String formatCategory(String cateRes) {
        if (cateRes !=null && cateRes.length()!=0) {
            switch (cateRes) {
            case "休闲益智":
            case "扑克棋牌":
            case "飞行射击":
            case "网络游戏":
            case "跑酷竞速":
            case "动作冒险":
            case "经营策略":
            case "体育竞技":
            case "角色扮演":
            case "辅助工具":
                return "游戏," + cateRes;
            default:
                return "软件," + cateRes;
            }
        } else {
            return "";
        }
    }

    public static float formatScore(String scoreRes) {
        if (scoreRes == null || scoreRes.equals("") || scoreRes.equals("暂无")) {
            return 0;
        } else {
            float tmp = Float.parseFloat(scoreRes.substring(0, scoreRes.length() - 1));
            return tmp / 10;
        }
    }

    public static String formatOsPerm(String osPermRes) {
        if (osPermRes == null || osPermRes.equals("")) {
            return "";
        } else {
            return osPermRes.substring(1, osPermRes.length() - 1);
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
