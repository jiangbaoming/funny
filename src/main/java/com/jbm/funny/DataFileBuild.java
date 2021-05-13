package com.jbm.funny;

import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;

import javax.swing.text.StyledEditorKit;
import java.lang.reflect.Array;
import java.util.*;

/**
 * @Author: 江宝明
 * @Description:
 * @Date:2021/04/05
 * @Modified By：
 */
public class DataFileBuild {


    public static void main(String[] args) {
        String [] files = {"TB_XDRY_JBXX.xlsx","TB_XDRY_CHXX.xlsx","TB_XDRY_CZXX.xlsx","TB_XDRY_DQGK.xlsx"};
        List<String> tempFiles = Arrays.asList(files);
        tempFiles.forEach(file ->{
            String filename = file.substring(0,file.lastIndexOf("."));
            List<Map<String,Object>> datas =  DataFileBuild.build(file);
            for(int i= 0;i<20;i++){
                // 通过工具类创建writer
                String fileName = filename+i+".xlsx";
                String tempPath =  "C:/Users/Administrator/Desktop/20210405/"+filename+"/"+fileName;
                ExcelWriter writer = ExcelUtil.getBigWriter(tempPath);
                List<Map<String,Object>> listTemp = new ArrayList<>();
                for (int k=0;k<datas.size();k++){
                    Map<String,Object> data = datas.get(k);
                    for(int j=0;j<100;j++){
                        Map<String,Object> temp = new HashMap<>();
                        temp.putAll(data);
                        temp.put("XYRBH",data.get("XYRBH").toString()+i+j);
                        // 一次性写出内容，使用默认样式，强制输出标题
                        listTemp.add(temp);
                    }

                }
                writer.write(listTemp, true);
                // 关闭writer，释放内存
                writer.close();
                System.out.println(file+":"+i+"------------------------->完成！");
            }




            System.out.println(file+"------------------------->完成！");
        });
    }
    public static  List<Map<String,Object>>  build(String fileName){
        String path = "C:/Users/Administrator/Desktop/20210402/"+fileName;
        ExcelReader reader = ExcelUtil.getReader(FileUtil.file(path));
        List<Map<String,Object>> list = reader.readAll();
        return list;
    }
}

interface  DataHandler{

    void handler (Map<String,Object> data);
}
