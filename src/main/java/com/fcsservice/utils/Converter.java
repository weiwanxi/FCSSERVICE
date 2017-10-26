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
}
