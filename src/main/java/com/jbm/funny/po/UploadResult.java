package com.jbm.funny.po;

/**
 * @Author: 江宝明
 * @Description:
 * @Date:2021/03/12
 * @Modified By：
 */
public class UploadResult {

    private int code;
    private String fileName;

    public UploadResult(int code, String fileName) {
        this.code = code;
        this.fileName = fileName;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public static UploadResult OK(){
        return new UploadResult(200,"file");
    }
}
