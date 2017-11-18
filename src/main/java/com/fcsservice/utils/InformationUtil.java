package com.fcsservice.utils;

import com.fcsservice.model.pojo.Information;
import org.dom4j.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by YE on 2017/11/10 16:40.
 */
public class InformationUtil {
    List<String> sort = new ArrayList<String>();
    List<String> p = new ArrayList<String>();
    List<String> img = new ArrayList<String>();
    List<String> img_intr = new ArrayList<String>();

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

    public Map<String,String[]> parseInformation(String informationContent){
        Map<String,String[]> map = new HashMap<String, String[]>();

        Document document = null;
        try {
            document = DocumentHelper.parseText(informationContent);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element root = document.getRootElement();
        listNodes(root);

        map.put("p",getArray(p));
        map.put("img",getArray(img));
        map.put("imgIntr",getArray(img_intr));
        map.put("sort",getArray(sort));
        return map;
    }

    private void listNodes(Element node){
        String text = node.getTextTrim();
        String name = node.getName();
        if("p".equals(name)){
            sort.add(0+"");
            p.add(text);
        }else if ("img".equals(name)){
            sort.add(1+"");
            img.add(text);
        }else if ("img-intr".equals(name)){
            sort.add(2+"");
            img_intr.add(text);
        }

        //同时迭代当前节点下面的所有子节点
        //使用递归
        Iterator<Element> iterator = node.elementIterator();
        while(iterator.hasNext()){
            Element e = iterator.next();
            listNodes(e);
        }
    }

    private String[] getArray(List<String> list){
        String[] array = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }
}
