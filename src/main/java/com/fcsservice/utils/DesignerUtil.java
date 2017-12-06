package com.fcsservice.utils;

import com.fcsservice.form.DesignerForm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YE on 2017/11/15 11:00.
 */
public class DesignerUtil {
    /**
     * 用于组装返回设计师列表数据
     * @param designerForms 设计师列表
     * @return Map
     */
    public Map<String,String[]> getDesignerMap(List<DesignerForm> designerForms){
        if (designerForms == null || designerForms.size() == 0)
            return null;

        Map<String,String[]> map = new HashMap<String, String[]>();
        int size = designerForms.size();

        String[] designerId = new String[size];
        String[] designerName = new String[size];
        String[] designerPortrait = new String[size];
        String[] designerWorkNumber = new String[size];
        String[] designerFollow = new String[size];

        for (int i = 0; i < size; i++) {
            designerId[i] = designerForms.get(i).getDesignerId();
            designerName[i] = designerForms.get(i).getDesignerName();
            designerPortrait[i] = designerForms.get(i).getDesignerPortrait();
            designerWorkNumber[i] = String.valueOf(designerForms.get(i).getWorkNumber());
            designerFollow[i] = designerForms.get(i).getFollow();
        }

        map.put("id",designerId);
        map.put("name",designerName);
        map.put("portrait",designerPortrait);
        map.put("number",designerWorkNumber);
        map.put("follow",designerFollow);

        return map;
    }

    /**
     * List取重复的值
     * @param list1 list1
     * @param list2 list2
     * @return list1
     */
    public List<String> getRepetition(List<String> list1,
                                      List<String> list2) {
        List<String> result = new ArrayList<String>();
        for (String designerId : list2) {//遍历list1
            if (list1.contains(designerId)) {//如果存在这个数
                result.add(designerId);//放进一个list里面，这个list就是交集
            }
        }
        return result;
    }

    /**
     * List取非重复的值
     * @param list1 list1
     * @param list2 list2
     * @return list1
     */
    public List<DesignerForm> getDesignerNotRepetition(List<DesignerForm> list1,
                                                       List<DesignerForm> list2) {
        List<DesignerForm> result = new ArrayList<DesignerForm>();
        DesignerForm form1,form2;

        if (list1 == null){
            return list2;
        }

        for (int i = 0; i < list2.size(); i++) {
            form2 = list2.get(i);
            boolean eq = false;
            for (int j = 0; j < list1.size(); j++) {
                form1 = list1.get(j);
                if (form1.getDesignerId().equals(form2.getDesignerId())){
                    eq = true;
                    break;
                }
            }
            if (!eq)
                result.add(form2);
        }
        return result;
    }
}