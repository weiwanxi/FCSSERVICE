package com.fcsservice.service;

import com.fcsservice.dao.ClothDao;
import com.fcsservice.dao.FabulousDao;
import com.fcsservice.model.pojo.Cloth;
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
public class ClothService {
    @Autowired
    ClothDao clothDao;
    @Autowired
    FabulousDao fabulousDao;

    public Map<String,String[]> getClothOrderByComment(int page, int number){
        List<Cloth> clothList = clothDao.getCostumeOrderByComment(page,number);

        return getMap(clothList,number);
    }

    public Map<String,String[]> getClothOrderByFabulous(int page, int number){
        List<Cloth> clothList = clothDao.getCostumeOrderByFabulous(page,number);

        return getMap(clothList,number);
    }

    private Map<String,String[]> getMap(List<Cloth> clothList,int number){
        Map<String,String[]> map = new HashMap<String, String[]>();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String[] id = new String[number];
        String[] image = new String[number];
        String[] title = new String[number];
        String[] time = new String[number];
        String[] fabulous = new String[number];

        if (clothList != null){
            for (int i=0;i<clothList.size();i++){
                id[i] = clothList.get(i).getClothId();
                image[i] = clothList.get(i).getClothPicture1();
                title[i] = clothList.get(i).getClothName();
                time[i] = format.format(clothList.get(i).getClothReltime());
                fabulous[i] = fabulousDao.getFabulous(clothList.get(i).getClothId())+"";
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
