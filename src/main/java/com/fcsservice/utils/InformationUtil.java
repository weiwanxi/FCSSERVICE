package com.fcsservice.utils;

import com.fcsservice.model.pojo.Information;
import org.dom4j.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by YE on 2017/11/10 16:40.
 */
public class InformationUtil {

    public String[] getInformationId(int number, List<Information> informationList){
        String[] idArray = new String[number];

        for (int i=0; i<number;i++){
            Information information = informationList.get(i);
            idArray[i] = information.getInformationId();
        }
        return idArray;
    }

    public String[] getInformationTitle(int number, List<Information> informationList){
        String[] titleArray = new String[number];

        for (int i=0; i<number;i++){
            Information information = informationList.get(i);
            titleArray[i] = information.getInformationTopic();
        }
        return titleArray;
    }

    public String[] getInformationImage(int number, List<Information> informationList){
        String[] imageArray = new String[number];

        for (int i=0; i<number;i++){
            Information information = informationList.get(i);
            String content = information.getInformationContent();

            //解析获取资讯中的第一张图片
            Document document = null;
            try {
                document = DocumentHelper.parseText(content);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
            Element imgElement = (Element) document.getRootElement().selectSingleNode("//img");
            String image;
            if (imgElement.getText() == null){
                image = "";
            }else {
                image= imgElement.getText();
            }
            imageArray[i] = image;
        }

        return imageArray;
    }

    public String[] getInformationTime(int number, List<Information> informationList){
        String[] timeArray = new String[number];

        for (int i=0; i<number;i++){
            Information information = informationList.get(i);
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = format.format(information.getInformationReltime());
            timeArray[i] = time;
        }
        return timeArray;
    }
}
