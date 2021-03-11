package com.jbm.funny;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import cn.hutool.core.io.FileUtil;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
/**
 * @Author: 江宝明
 * @Description:
 * @Date:2021/03/05
 * @Modified By：
 */

/**
 * <h5>功能:视频文件处理工具类</h5>
 */
public class ToolVideo {

    public static void main(String[] args) {
        ToolVideo.videoToTextVideo(new File("E:/wallpapercache/dynamic/kwp_33fa96d9eaad97074dfb1d02cf2d26c4.mp4"));
    }

    public static void videoToTextVideo(File file) {
        FFmpegFrameGrabber grabber;
        try {
            grabber = FFmpegFrameGrabber.createDefault(file);
            String fileName = file.getName();
            String targetFileName = new StringBuilder(fileName).insert(0, "temp").toString();
            String targetFilePath = file.getParent() + "/" + targetFileName;
            File targetFile = FileUtil.file(targetFilePath);
            FFmpegFrameRecorder recorder = FFmpegFrameRecorder.createDefault(targetFile, grabber.getImageWidth(), grabber.getImageHeight());

            // 视频相关配置，取原视频配置
            recorder.setFrameRate(grabber.getFrameRate());
            recorder.setVideoCodec(grabber.getVideoCodec());
            recorder.setVideoBitrate(grabber.getVideoBitrate());
            recorder.setFormat(grabber.getFormat());
            // 音频相关配置，取原音频配置
            recorder.setSampleRate(grabber.getSampleRate());
            recorder.setAudioCodec(avcodec.AV_CODEC_ID_MP3);

            recorder.start();
            grabber.start();
            // 视频总帧数
            int videoLength = grabber.getLengthInFrames();
            Frame frame = null;
            Java2DFrameConverter converter = new Java2DFrameConverter();
            for (int i = 0; i < videoLength; i++) {
                frame = grabber.grabFrame();
                if (null != frame) {
                    BufferedImage bi = converter.getBufferedImage(frame);
                    // 绘制图片
                    BufferedImage out = ImageTool.toTextImage(bi);
                    frame.image = converter.getFrame(out).image;
                    recorder.record(frame);
                }
            }
            grabber.stop();
            recorder.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
