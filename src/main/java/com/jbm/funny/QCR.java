package com.jbm.funny;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

import java.io.File;

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
            File imageFile = new File("C:\\Users\\Administrator\\Desktop\\08982b9cc02f081ffc549187cc2faccc.jpg");
            //创建tess对象
            ITesseract instance = new Tesseract();
            //设置训练文件目录
            instance.setDatapath("tessdata");
            //设置训练语言
            instance.setLanguage("chi_sim");
            //执行转换
            String result = instance.doOCR(imageFile);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
