package com.fcsservice.service;

import com.fcsservice.dao.CostumeDao;
import com.fcsservice.dao.FabulousDao;
import com.fcsservice.model.pojo.Costume;
import org.apache.log4j.Logger;
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
        List<Costume> costumeList = costumeDao.getCostumeOrderByComment(page,number);

        return getMap(costumeList,number);
    }

    public Map<String,String[]> getCostumeOrderByFabulous(int page, int number){
        List<Costume> costumeList = costumeDao.getCostumeOrderByFabulous(page,number);

        return getMap(costumeList,number);
    }

    private Map<String,String[]> getMap(List<Costume> costumeList,int number){
        Map<String,String[]> map = new HashMap<String, String[]>();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String[] id = new String[number];
        String[] image = new String[number];
        String[] title = new String[number];
        String[] time = new String[number];
        String[] fabulous = new String[number];

        if (costumeList != null){
            for (int i=0;i<costumeList.size();i++){
                id[i] = costumeList.get(i).getCostumeId();
                image[i] = costumeList.get(i).getCostumePicture1();
                title[i] = costumeList.get(i).getCostumeName();
                time[i] = format.format(costumeList.get(i).getCostumeReltime());
                fabulous[i] = fabulousDao.getFabulous(costumeList.get(i).getCostumeId())+"";
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
