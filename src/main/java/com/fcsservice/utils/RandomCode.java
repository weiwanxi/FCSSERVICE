package com.fcsservice.utils;

import java.util.Random;

/**
 * Created by YE on 2017/10/27 17:32.
 */
public class RandomCode {

    /**
     * 获取随机四个数字
     * @return String
     */
    public static String getCode(){
        String str="0123456789";
        StringBuilder sb=new StringBuilder(4);
        for(int i=0;i<4;i++)
        {
            char ch=str.charAt(new Random().nextInt(str.length()));
            sb.append(ch);
        }
        return sb.toString();
    }
}
