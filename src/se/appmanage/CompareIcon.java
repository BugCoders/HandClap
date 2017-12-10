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
    // ȫ����
    /*public static void main(String[] args) {
        downloadPicture("https://img.25pp.com/uploadfile/app/icon/20171202/1512166189677087.jpg@140w_140h", "1");
        downloadPicture(
                "http://android-artworks.25pp.com/fs08/2017/08/01/6/110_fb015d8155303d523fe69724528410f3_con_130x130.png",
                "2");
        //convertPngToJpg("2.png");
        try {
            compareImage("1.jpg", "2.png");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }*/

    public static double compareImage(String imgPath1, String imgPath2) throws IOException {
        // ��ȡͼ��
        File imageFile = new File(imgPath1);
        Image image = ImageIO.read(imageFile);
        // ת�����Ҷ�
        image = toGrayscale(image);
        // ��С��32x32������ͼ
        image = scale(image);
        // ��ȡ�Ҷ���������
        int[] pixels = getPixels(image);
        // ��ȡƽ���Ҷ���ɫ
        int averageColor = getAverageOfPixelArray(pixels);
        // ��ȡ�Ҷ����صıȽ����飨��ͼ��ָ�����У�
        pixels = getPixelDeviateWeightsArray(pixels, averageColor);

        File imageFile1 = new File(imgPath2);
        Image image1 = ImageIO.read(imageFile1);
        // ת�����Ҷ�
        image1 = toGrayscale(image1);
        // ��С��32x32������ͼ
        image1 = scale(image1);
        // ��ȡ�Ҷ���������
        int[] pixels1 = getPixels(image1);
        // ��ȡƽ���Ҷ���ɫ
        int averageColor1 = getAverageOfPixelArray(pixels1);
        // ��ȡ�Ҷ����صıȽ����飨��ͼ��ָ�����У�
        pixels1 = getPixelDeviateWeightsArray(pixels1, averageColor1);
        // ��ȡ����ͼ�ĺ������루������һ��ͼҲ�Ѿ������沽��õ��ҶȱȽ����飩
        int hammingDistance = getHammingDistance(pixels, pixels1);
        // ͨ����������������ƶȣ�ȡֵ��Χ [0.0, 1.0]
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
            // TYPE_INT_RGB:����һ��RBGͼ��24λ��ȣ��ɹ���32λͼת����24λ
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
            if (name.equals("1"))
                imageName = name + ".jpg";
            else
                imageName = name + ".png";
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

    // ������Image����ͼ��ת��ΪBufferedImage���ͣ������������
    public static BufferedImage convertToBufferedFrom(Image srcImage) {
        BufferedImage bufferedImage = new BufferedImage(srcImage.getWidth(null), srcImage.getHeight(null),
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bufferedImage.createGraphics();
        g.drawImage(srcImage, null, null);
        g.dispose();
        return bufferedImage;
    }

    // ת�����Ҷ�ͼ
    public static BufferedImage toGrayscale(Image image) {
        BufferedImage sourceBuffered = convertToBufferedFrom(image);
        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
        ColorConvertOp op = new ColorConvertOp(cs, null);
        BufferedImage grayBuffered = op.filter(sourceBuffered, null);
        return grayBuffered;
    }

    // ������32x32��������ͼ
    public static Image scale(Image image) {
        image = image.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        return image;
    }

    // ��ȡ��������
    public static int[] getPixels(Image image) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        int[] pixels = convertToBufferedFrom(image).getRGB(0, 0, width, height, null, 0, width);
        return pixels;
    }

    // ��ȡ�Ҷ�ͼ��ƽ��������ɫֵ
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

    // ��ȡ�Ҷ�ͼ�����رȽ����飨ƽ��ֵ����
    public static int[] getPixelDeviateWeightsArray(int[] pixels, final int averageColor) {
        Color color;
        int[] dest = new int[pixels.length];
        for (int i = 0; i < pixels.length; i++) {
            color = new Color(pixels[i], true);
            dest[i] = color.getRed() - averageColor > 0 ? 1 : 0;
        }
        return dest;
    }

    // ��ȡ��������ͼ��ƽ�����رȽ�����ĺ������루����Խ�����Խ��
    public static int getHammingDistance(int[] a, int[] b) {
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i] == b[i] ? 0 : 1;
        }
        return sum;
    }

    // ͨ����������������ƶ�
    public static double calSimilarity(int hammingDistance) {
        int length = 32 * 32;
        double similarity = (length - hammingDistance) / (double) length;

        // ʹ��ָ�����ߵ������ƶȽ��
        similarity = java.lang.Math.pow(similarity, 2);
        return similarity;
    }
}
