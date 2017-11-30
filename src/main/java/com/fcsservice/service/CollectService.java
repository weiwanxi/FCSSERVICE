package com.fcsservice.service;

import com.fcsservice.dao.*;
import com.fcsservice.model.pojo.*;
import com.fcsservice.utils.InformationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by YE on 2017/11/17 23:54.
 */

@Service
@Transactional
public class CollectService {
    @Autowired
    CollectDao collectDao;
    @Autowired
    AccountDao accountDao;
    @Autowired
    InformationDao informationDao;
    @Autowired
    WorkDao workDao;
    @Autowired
    ClothDao clothDao;
    @Autowired
    CostumeDao costumeDao;

    public String addCollect(String informationId,String userId,int collectType){
        Collect collect = new Collect();
        if (accountDao.getUserAccountById(userId) == null) {
            return "该用户不存在";
        }

        collect.setCollectId(UUID.randomUUID().toString().replaceAll("-", ""));
        collect.setCollectionId(informationId);
        collect.setCollectorId(userId);
        collect.setCollectType(collectType);
        collect.setCollectTime(new Date());

        collectDao.addCollect(collect);
        return null;
    }

    public String deleteCollect(String informationId,String userId){
        if (accountDao.getUserAccountById(userId) == null) {
            return "该用户不存在";
        }

        Collect collect = collectDao.getCollectByIU(informationId,userId);
        if (collect == null){
            return "该收藏不存在";
        }else {
            collectDao.deleteCollect(collect.getCollectId());
        }

        return null;
    }

    public Map<String,String[]> getCollect(String typee,String sort,String userId){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int type = Integer.parseInt(typee);
        Map<String,String[]> map = new HashMap<String, String[]>();

        List<Collect> collectList;
        if ("desc".equals(sort)){
            collectList = collectDao.getCollectDesc(userId,type);
        }else if ("asc".equals(sort)){
            collectList = collectDao.getCollectAsc(userId,type);
        }else {
            return null;
        }

        if (collectList != null){
            String[] id = new String[collectList.size()];
            String[] image = new String[collectList.size()];
            String[] title = new String[collectList.size()];
            String[] time = new String[collectList.size()];

            //获取收藏的资讯
            if (type == 1){
                for (int i=0;i<collectList.size();i++){
                    Information information = informationDao.getInformationById(collectList.get(i).getCollectionId());
                    if (information != null){
                        id[i] = information.getInformationId();
                        image[i] = new InformationUtil().getFirstImage(information);
                        title[i] = information.getInformationTopic();
                        time[i] = format.format(collectList.get(i).getCollectTime());
                    }else {
                        id[i] = "";
                        image[i] = "";
                        title[i] = "";
                        time[i] = format.format(collectList.get(i).getCollectTime());
                    }
                }
            }else if (type == 2){
                for (int i=0;i<collectList.size();i++){
                    Work work = workDao.getWorkById(collectList.get(i).getCollectionId());
                    if (work != null){
                        id[i] = work.getWorkId();
                        image[i] = work.getWorkPicture1();
                        title[i] = work.getWorkName();
                        time[i] = format.format(collectList.get(i).getCollectTime());
                    }else {
                        id[i] = "";
                        image[i] = "";
                        title[i] = "";
                        time[i] = format.format(collectList.get(i).getCollectTime());
                    }
                }
            }else if (type == 3){
                for (int i=0;i<collectList.size();i++){
                    Cloth cloth = clothDao.getClothById(collectList.get(i).getCollectionId());
                    if (cloth != null){
                        id[i] = cloth.getClothId();
                        image[i] = cloth.getClothPicture1();
                        title[i] = cloth.getClothName();
                        time[i] = format.format(collectList.get(i).getCollectTime());
                    }else {
                        id[i] = "";
                        image[i] = "";
                        title[i] = "";
                        time[i] = format.format(collectList.get(i).getCollectTime());
                    }
                }
            }else if (type == 4){
                for (int i=0;i<collectList.size();i++){
                    Costume costume = costumeDao.getCostumeById(collectList.get(i).getCollectionId());
                    if (costume != null){
                        id[i] = costume.getCostumeId();
                        image[i] = costume.getCostumePicture1();
                        title[i] = costume.getCostumeName();
                        time[i] = format.format(collectList.get(i).getCollectTime());
                    }else {
                        id[i] = "";
                        image[i] = "";
                        title[i] = "";
                        time[i] = format.format(collectList.get(i).getCollectTime());
                    }
                }
            }

            map.put("id",id);
            map.put("image",image);
            map.put("title",title);
            map.put("time",time);
        }else {
            return null;
        }

        return map;
    }
}
