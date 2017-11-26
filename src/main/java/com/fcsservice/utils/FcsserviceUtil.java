package com.fcsservice.utils;

import com.fcsservice.controller.AlbumController;
import org.springframework.util.Base64Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

/**
 * Created by YE on 2017/11/8 22:45.
 */
public class FcsserviceUtil {
    /*
     * 用于判断设计师身份
     */
    public final static int DESIGNER = 1;
    public final static int NORMALUSER = 2;

    /*
     *  数据库字典表值，用于查询设计标签
     */
    public final static String  DESIGN_TAG = "设计标签";
    /*
     *  数据库字典表值，用于查询服装风格
     */
    public final static String  STYLE_TAG = "服装风格";
    /*
     *  数据库字典表值，用于查询服装款式
     */
    public final static String  MODEL_TAG = "服装款式";

    /*
     *  用于获取资讯表中的置顶信息
     */
    public final static int TOP_INFORMATION = 2;
    /*
     * 用于判断信息类别
     */
    public final static int INFORMATION = 1;
    public final static int WORK = 2;
    public final static int COSTUME = 3;
    public final static int CLOTH = 4;
    /*
     * 用于判断是否已点赞，收藏 0为true，1为false
     */
    public final static int FCtrue = 0;
    public final static int FCfalse = 1;
    public final static String CollectTrue = "collect";
    public final static String CollectFalse = "uncollect";
    public final static String FabulousTrue = "fabulous";
    public final static String FabulousFalse = "unfabulous";
    /*
     * 用于获取页面数据控制一页数据数量
     */
    public final static int PageNumber = 6;

    /*
     * 头像保存路径
     */
    public final String IMAGEPATH = this.getClass().getClassLoader().getResource("").getPath().replace("/classes/","/image/");

    /*
     * 专辑封面保存路径
     */
    public final String IMAGE1PATH = this.getClass().getClassLoader().getResource("").getPath().replace("/classes/","/image1/");

    public String saveImage(String savePath,String base64Data){
        String fileName;

        try {
            String dataPrix;
            String data;

            if(base64Data == null || "".equals(base64Data)){
                throw new Exception("上传失败，上传图片数据为空");
            }else{
                String [] d = base64Data.split("base64,");
                if(d.length == 2){
                    dataPrix = d[0];
                    data = d[1];
                }else{
                    throw new Exception("上传失败，数据不合法");
                }
            }

            String suffix;
            if("data:image/jpeg;".equalsIgnoreCase(dataPrix)){//data:image/jpeg;base64,base64编码的jpeg图片数据
                suffix = ".jpg";
            } else if("data:image/x-icon;".equalsIgnoreCase(dataPrix)){//data:image/x-icon;base64,base64编码的icon图片数据
                suffix = ".ico";
            } else if("data:image/gif;".equalsIgnoreCase(dataPrix)){//data:image/gif;base64,base64编码的gif图片数据
                suffix = ".gif";
            } else if("data:image/png;".equalsIgnoreCase(dataPrix)){//data:image/png;base64,base64编码的png图片数据
                suffix = ".png";
            }else{
                throw new Exception("上传图片格式不合法");
            }
            //因为BASE64Decoder的jar问题，此处使用spring框架提供的工具包
            byte[] bs = Base64Utils.decodeFromString(data);

            fileName = UUID.randomUUID().toString().replaceAll("-", "") + suffix;
            String path = savePath+fileName;

            File file = new File(path);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bs);
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }


        return fileName;
    }
}
