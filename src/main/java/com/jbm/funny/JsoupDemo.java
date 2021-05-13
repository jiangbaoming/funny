package com.jbm.funny;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

/**
 * @Author: 江宝明
 * @Description:
 * @Date:2021/03/22
 * @Modified By：
 */
public class JsoupDemo {


    public static void main(String[] args) throws IOException {
        //从URL加载HTML
        Document document = Jsoup.connect("http://www.baidu.com").userAgent("Mozilla").get();
        Element  kw = document.getElementById("kw");
        kw.val("迪丽热巴");

        Element   su= document.getElementById("su");


        String title = document.title();
        //获取html中的标题
        System.out.println("title :" + title);
    }

}
