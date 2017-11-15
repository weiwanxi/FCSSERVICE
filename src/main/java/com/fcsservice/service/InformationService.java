package com.fcsservice.service;

import com.fcsservice.dao.InformationDao;
import com.fcsservice.model.pojo.Information;
import com.fcsservice.utils.InformationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YE on 2017/11/10 16:19.
 */

@Service
@Transactional
public class InformationService {
    @Autowired
    InformationDao informationDao;

    public Map<String,String[]> getTopInformation(){
        Map<String,String[]> topInformation = new HashMap<String, String[]>();

        List<Information> informationList = informationDao.getTopInformation();
        InformationUtil informationUtil = new InformationUtil();
        String[] titleArray = new String[4];
        String[] imageArray = new String[4];
        String[] idArray = new String[4];

        titleArray = informationUtil.getInformationTitle(titleArray.length,informationList);
        imageArray = informationUtil.getInformationImage(imageArray.length,informationList);
        idArray = informationUtil.getInformationId(idArray.length,informationList);

        topInformation.put("title",titleArray);
        topInformation.put("image",imageArray);
        topInformation.put("id",idArray);

        return topInformation;
    }

    public Map<String,String[]> getInformationList(){
        Map<String,String[]> informationMap = new HashMap<String, String[]>();

        List<Information> informationList = informationDao.getInformationList();

        InformationUtil informationUtil = new InformationUtil();
        String[] idArray = new String[5];
        String[] imageArray = new String[5];
        String[] titleArray = new String[5];
        String[] timeArray = new String[5];

        idArray = informationUtil.getInformationId(idArray.length,informationList);
        titleArray = informationUtil.getInformationTitle(titleArray.length,informationList);
        imageArray = informationUtil.getInformationImage(imageArray.length,informationList);
        timeArray = informationUtil.getInformationTime(timeArray.length,informationList);

        informationMap.put("id",idArray);
        informationMap.put("title",titleArray);
        informationMap.put("image",imageArray);
        informationMap.put("time",timeArray);

        return informationMap;
    }

    public Map<String,String[]> getNewInformationList(String newInformation){
        Map<String,String[]> informationMap = new HashMap<String, String[]>();

        List<Information> informationList = informationDao.getNewInformationList(newInformation);
        if (informationList == null || informationList.size() < 1){
            return null;
        }

        InformationUtil informationUtil = new InformationUtil();
        String[] idArray;
        String[] imageArray;
        String[] titleArray;
        String[] timeArray;

        idArray = informationUtil.getInformationId(informationList.size(),informationList);
        titleArray = informationUtil.getInformationTitle(informationList.size(),informationList);
        imageArray = informationUtil.getInformationImage(informationList.size(),informationList);
        timeArray = informationUtil.getInformationTime(informationList.size(),informationList);

        informationMap.put("id",idArray);
        informationMap.put("title",titleArray);
        informationMap.put("image",imageArray);
        informationMap.put("time",timeArray);

        return informationMap;
    }

    public Map<String,String[]> getOldInformationList(String oldInformation){
        Map<String,String[]> informationMap = new HashMap<String, String[]>();

        List<Information> informationList = informationDao.getOldInformationList(oldInformation);
        if (informationList == null || informationList.size() < 1){
            return null;
        }

        InformationUtil informationUtil = new InformationUtil();
        String[] idArray;
        String[] titleArray;
        String[] imageArray;
        String[] timeArray;

        idArray = informationUtil.getInformationId(informationList.size(),informationList);
        titleArray = informationUtil.getInformationTitle(informationList.size(),informationList);
        imageArray = informationUtil.getInformationImage(informationList.size(),informationList);
        timeArray = informationUtil.getInformationTime(informationList.size(),informationList);

        informationMap.put("id",idArray);
        informationMap.put("title",titleArray);
        informationMap.put("image",imageArray);
        informationMap.put("time",timeArray);

        return informationMap;
    }
}
