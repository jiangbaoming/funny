package com.jbm.funny.controller;

import cn.hutool.core.io.FileUtil;
import com.baidu.aip.ocr.AipOcr;
import com.jbm.funny.po.UploadResult;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;

/**
 * @Author: 江宝明
 * @Description:
 * @Date:2021/03/12
 * @Modified By：
 */
@Controller
@RequestMapping("/upload")
public class UploadFileController {

    @Autowired
    private AipOcr aipOcr;

    @RequestMapping("/img")
    @ResponseBody
    public UploadResult uploadFile(HttpServletResponse response, HttpServletRequest request, MultipartFile file) {
        try {

            final String filePath = ResourceUtils.getURL("classpath:").getPath() + "files" + "/" + file.getOriginalFilename();
            File target = FileUtil.file(filePath);
            FileUtil.writeFromStream(file.getInputStream(), target);
            JSONObject jsonObject = aipOcr.basicAccurateGeneral(filePath, new HashMap<String, String>());
            JSONArray words = jsonObject.getJSONArray("words_result");
            for (int i = 0; i < words.length(); i++) {
                String word = words.getJSONObject(i).getString("words");
                System.out.println(word);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return UploadResult.OK();
    }
}
