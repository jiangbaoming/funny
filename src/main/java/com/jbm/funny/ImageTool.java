package com.jbm.funny;

import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * @Author: 江宝明
 * @Description:
 * @Date:2021/03/10
 * @Modified By：
 */
public class ImageTool {

    static String ascii = "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\\\"^`'.";

    static String base = "@#&$%*o!;.";//小帅丶使用这个字符
    /**
     * 图片类型
     */
    private static final int IMAGE_TYPE = BufferedImage.TYPE_INT_RGB;

    public static String createAsciiPic(String filePath) {
        try {
            return ImageTool.txtToImageByBase64(ImageIO.read(new File(filePath)));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 图片转字符再保存为图片 只返回图片的base64
     *
     * @param bi 原图
     * @return String
     */
    public static String txtToImageByBase64(BufferedImage bi) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            BufferedImage bufferedImage = toTextImage(bi);
            ImageIO.write(bufferedImage, "jpg", out);
            BASE64Encoder base64Encoder = new BASE64Encoder();
            String base64 = base64Encoder.encode(out.toByteArray());
            return base64;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage toTextImage(BufferedImage in) {
        int type = in.getType();
        int width = in.getWidth();
        int height = in.getHeight();
        int minx = in.getMinX();
        int miny = in.getMinY();
        int speed = 7;
        BufferedImage bufferedImage = new BufferedImage(width, height, type);
        // 获取图像上下文
        Graphics g = createGraphics(bufferedImage, width, height, speed);
        for (int i = miny; i < height; i += speed) {
            for (int j = minx; j < width; j += speed) {
                int pixel = in.getRGB(j, i); // 下面三行代码将一个数字转换为RGB数字
                int red = (pixel & 0xff0000) >> 16;
                int green = (pixel & 0xff00) >> 8;
                int blue = (pixel & 0xff);
                float gray = 0.299f * red + 0.578f * green + 0.114f * blue;
                int index = Math.round(gray * (base.length() + 1) / 255);
                String c = index >= base.length() ? " " : String.valueOf(base.charAt(index));
                g.drawString(String.valueOf(c), j, i);
            }
        }
        g.dispose();
        return bufferedImage;
    }

    /**
     * 画板默认一些参数设置
     *
     * @param image  图片
     * @param width  图片宽
     * @param height 图片高
     * @param size   字体大小
     * @return
     */
    private static Graphics createGraphics(BufferedImage image, int width,
                                           int height, int size) {
        Graphics g = image.createGraphics();
        g.setColor(null); // 设置背景色
        g.fillRect(0, 0, width, height);// 绘制背景
        g.setColor(Color.BLACK); // 设置前景色
        g.setFont(new Font("微软雅黑", Font.PLAIN, size)); // 设置字体
        return g;
    }

    public static void main(String[] args) {
        System.out.println(ImageTool.createAsciiPic("E:/images/timg.jpg"));
    }
}
