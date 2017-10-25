package com.fcsservice.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by YE on 2017/10/25.
 */
public class Converter {

    //获取字符串格式的当前时间
    public String getNowTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }

    //将字符串转化成int数组，传入字符串与分隔符
    public int[] getIntegerArrayByString(String str,String separator){
        String strArray[] = str.split(separator);
        int[] intArray = new int[strArray.length];
        for (int i=0;i<strArray.length;i++){
            intArray[i] = Integer.parseInt(strArray[i]);
        }
        return intArray;
    }
}
