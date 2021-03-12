package com.jbm.funny;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.Word;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.List;

/**
 * @Author: 江宝明
 * @Description:
 * @Date:2021/03/11
 * @Modified By：
 */
public class QCR {


    public static void main(String[] args) {
        try {
            //加载待读取图片
            File imageFile = new File("C:\\Users\\Administrator\\Desktop\\下载.png");
            //创建tess对象
            ITesseract instance = new Tesseract();
            //设置训练文件目录
            instance.setDatapath("tessdata");
            //设置训练语言
            instance.setLanguage("chi_sim");
            //执行转换
            //String result = instance.doOCR(imageFile);
            List<Word> words = instance.getWords(ImageIO.read(imageFile), 0);
            for (Word word : words ) {
                System.out.println(word.getText());
            }
           // System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
