package se.appmanage;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

public class CompareIcon {
    // 全流程
    public static void main(String[] args) {
        downloadPicture("https://img.25pp.com/uploadfile/app/icon/20171211/1512976568109520.jpg@140w_140h", "1");
        downloadPicture(
                "http://android-artworks.25pp.com/fs08/2016/11/21/11/106_31e1fced509900af481c2395e430a0f7_con_130x130.png",
                "2");
        //convertPngToJpg("2.png");
        try {
            compareImage("1.jpg", "2.png");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static double compareImage(String imgPath1, String imgPath2) throws IOException {
        // 获取图像
        File imageFile = new File(imgPath1);
        Image image = ImageIO.read(imageFile);
        // 转换至灰度
        image = toGrayscale(image);
        // 缩小成32x32的缩略图
        image = scale(image);
        // 获取灰度像素数组
        int[] pixels = getPixels(image);
        // 获取平均灰度颜色
        int averageColor = getAverageOfPixelArray(pixels);
        // 获取灰度像素的比较数组（即图像指纹序列）
        pixels = getPixelDeviateWeightsArray(pixels, averageColor);

        File imageFile1 = new File(imgPath2);
        Image image1 = ImageIO.read(imageFile1);
        // 转换至灰度
        image1 = toGrayscale(image1);
        // 缩小成32x32的缩略图
        image1 = scale(image1);
        // 获取灰度像素数组
        int[] pixels1 = getPixels(image1);
        // 获取平均灰度颜色
        int averageColor1 = getAverageOfPixelArray(pixels1);
        // 获取灰度像素的比较数组（即图像指纹序列）
        pixels1 = getPixelDeviateWeightsArray(pixels1, averageColor1);
        // 获取两个图的汉明距离（假设另一个图也已经按上面步骤得到灰度比较数组）
        int hammingDistance = getHammingDistance(pixels, pixels1);
        // 通过汉明距离计算相似度，取值范围 [0.0, 1.0]
        double similarity = calSimilarity(hammingDistance);
        System.out.println(similarity);
        return similarity;

    }
    public static void convertPngToJpg(String imageName) {
        
            /*
             * File f = new File(imageName); f.canRead(); BufferedImage src =
             * ImageIO.read(f); ImageIO.write(src, "jpg", new File("2.jpg"));
             */
            BufferedImage bufferedImage=null;
            try {
                bufferedImage = ImageIO.read(new File(imageName));
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

            // create a blank, RGB, same width and height, and a white background
            BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(),
                    BufferedImage.TYPE_INT_RGB);
            // TYPE_INT_RGB:创建一个RBG图像，24位深度，成功将32位图转化成24位
            newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);

            // write to jpeg file
            try {
                ImageIO.write(newBufferedImage, "jpg", new File("2.jpg"));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        
    }
    public static void downloadPicture(String picUrl, String name) {
        URL url = null;
        try {
            url = new URL(picUrl);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());
            String imageName = null;
            if (name.equals("2"))
                imageName = name + ".png";
            else
                imageName = name + ".jpg";
            File f = new File(imageName.trim());
            FileOutputStream fileOutputStream = new FileOutputStream(f);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = dataInputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, length);
            }
            while(!f.canRead());
            dataInputStream.close();
            fileOutputStream.close();
            
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 将任意Image类型图像转换为BufferedImage类型，方便后续操作
    public static BufferedImage convertToBufferedFrom(Image srcImage) {
        BufferedImage bufferedImage = new BufferedImage(srcImage.getWidth(null), srcImage.getHeight(null),
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bufferedImage.createGraphics();
        g.drawImage(srcImage, null, null);
        g.dispose();
        return bufferedImage;
    }

    // 转换至灰度图
    public static BufferedImage toGrayscale(Image image) {
        BufferedImage sourceBuffered = convertToBufferedFrom(image);
        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
        ColorConvertOp op = new ColorConvertOp(cs, null);
        BufferedImage grayBuffered = op.filter(sourceBuffered, null);
        return grayBuffered;
    }

    // 缩放至32x32像素缩略图
    public static Image scale(Image image) {
        image = image.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        return image;
    }

    // 获取像素数组
    public static int[] getPixels(Image image) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        int[] pixels = convertToBufferedFrom(image).getRGB(0, 0, width, height, null, 0, width);
        return pixels;
    }

    // 获取灰度图的平均像素颜色值
    public static int getAverageOfPixelArray(int[] pixels) {
        Color color;
        long sumRed = 0;
        for (int i = 0; i < pixels.length; i++) {
            color = new Color(pixels[i], true);
            sumRed += color.getRed();
        }
        int averageRed = (int) (sumRed / pixels.length);
        return averageRed;
    }

    // 获取灰度图的像素比较数组（平均值的离差）
    public static int[] getPixelDeviateWeightsArray(int[] pixels, final int averageColor) {
        Color color;
        int[] dest = new int[pixels.length];
        for (int i = 0; i < pixels.length; i++) {
            color = new Color(pixels[i], true);
            dest[i] = color.getRed() - averageColor > 0 ? 1 : 0;
        }
        return dest;
    }

    // 获取两个缩略图的平均像素比较数组的汉明距离（距离越大差异越大）
    public static int getHammingDistance(int[] a, int[] b) {
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i] == b[i] ? 0 : 1;
        }
        return sum;
    }

    // 通过汉明距离计算相似度
    public static double calSimilarity(int hammingDistance) {
        int length = 32 * 32;
        double similarity = (length - hammingDistance) / (double) length;

        // 使用指数曲线调整相似度结果
        similarity = java.lang.Math.pow(similarity, 2);
        return similarity;
    }
}
