package com.fcsservice.service;

import com.fcsservice.dao.CostumeDao;
import com.fcsservice.dao.FabulousDao;
import com.fcsservice.form.CostumeForm;
import com.fcsservice.model.pojo.Costume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YE on 2017/11/19 15:35.
 */

@Service
@Transactional
public class CostumeService {
    @Autowired
    CostumeDao costumeDao;
    @Autowired
    FabulousDao fabulousDao;

    public Map<String,String[]> getCostumeOrderByComment(int page, int number){
        Map<String,String[]> map = new HashMap<String, String[]>();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String[] id = new String[number];
        String[] image = new String[number];
        String[] title = new String[number];
        String[] time = new String[number];
        String[] fabulous = new String[number];
        List<Costume> costumeFormList = costumeDao.getCostumeOrderByComment(page,number);
        if (costumeFormList != null){
            for (int i=0;i<costumeFormList.size();i++){
                id[i] = costumeFormList.get(i).getCostumeId();
                image[i] = costumeFormList.get(i).getCostumePicture1();
                title[i] = costumeFormList.get(i).getCostumeName();
                time[i] = format.format(costumeFormList.get(i).getCostumeReltime());
                fabulous[i] = fabulousDao.getFabulous(costumeFormList.get(i).getCostumeId())+"";
            }
            map.put("id",id);
            map.put("image",image);
            map.put("title",title);
            map.put("time",time);
            map.put("fabulous",fabulous);
        }else {
            return null;
        }
        return map;
    }

    public Map<String,String[]> getCostumeOrderByFabulous(int page, int number){
        Map<String,String[]> map = new HashMap<String, String[]>();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String[] id = new String[number];
        String[] image = new String[number];
        String[] title = new String[number];
        String[] time = new String[number];
        String[] fabulous = new String[number];
        List<Costume> costumeFormList = costumeDao.getCostumeOrderByFabulous(page,number);
        if (costumeFormList != null){
            for (int i=0;i<costumeFormList.size();i++){
                if (costumeFormList.get(i).getCostumeId() == null){
                    continue;
                }
                id[i] = costumeFormList.get(i).getCostumeId();
                image[i] = costumeFormList.get(i).getCostumePicture1();
                title[i] = costumeFormList.get(i).getCostumeName();
                time[i] = format.format(costumeFormList.get(i).getCostumeReltime());
                fabulous[i] = fabulousDao.getFabulous(costumeFormList.get(i).getCostumeId())+"";
            }
            map.put("id",id);
            map.put("image",image);
            map.put("title",title);
            map.put("time",time);
            map.put("fabulous",fabulous);
        }else {
            return null;
        }
        return map;
    }
}
