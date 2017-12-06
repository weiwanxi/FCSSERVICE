package com.fcsservice.service;

import com.fcsservice.dao.CollectDao;
import com.fcsservice.dao.CommentDao;
import com.fcsservice.dao.FabulousDao;
import com.fcsservice.dao.InformationDao;
import com.fcsservice.model.pojo.Information;
import com.fcsservice.utils.FcsserviceUtil;
import com.fcsservice.utils.InformationUtil;
import com.fcsservice.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    @Autowired
    FabulousDao fabulousDao;
    @Autowired
    CommentDao commentDao;
    @Autowired
    CollectDao collectDao;

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

    public Result getInformationById(String informationId,String userId){
        Result result = new Result();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String,String> map = new HashMap<String, String>();
        Map<String,String[]> contentMap = new HashMap<String, String[]>();
        Map<String,int[]> otherMap = new HashMap<String, int[]>();
        int[] other = new int[4];

        Information information = informationDao.getInformationById(informationId);
        if (information != null) {
            //获取资讯标题等
            map.put("informationId", information.getInformationId());
            map.put("informationTopic", information.getInformationTopic());
            map.put("informationAuthor", information.getInformationAuthor());
            map.put("informationReltime", format.format(information.getInformationReltime()));
            map.put("informationFabulous", String.valueOf(information.getInformationFabulous()));

            //提取资讯内容
            contentMap = new InformationUtil().parseInformation(information.getInformationContent());

            //获取点赞，评论，收藏信息
            other[0] = fabulousDao.getFabulousType(informationId,userId)?FcsserviceUtil.FCtrue:FcsserviceUtil.FCfalse;
            other[1] = fabulousDao.getFabulous(informationId);
            other[2] = commentDao.getCommentNumber(informationId);
            other[3] = collectDao.getCollectType(informationId,userId)?FcsserviceUtil.FCtrue:FcsserviceUtil.FCfalse;
            otherMap.put("other",other);
        }

        if (map.size()>0 && contentMap!=null && contentMap.size()>0){
            result.setObj(map);
            result.setObj1(contentMap);
            result.setObj2(otherMap);
            result.setCode(Result.SUCCESS);
        }else {
            result.setCode(Result.FAIL);
            result.setMsg("该资讯不存在");
        }

        return result;
    }

    public Map<String,String[]> getInformationBySearch(String searchText,int page,int number){
        Map<String,String[]> map = new HashMap<String, String[]>();
        List<Information> informationList = informationDao.getInformationBySearch(searchText,page,number);
        if (informationList != null){
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String[] id = new String[informationList.size()];
            String[] image = new String[informationList.size()];
            String[] title = new String[informationList.size()];
            String[] time = new String[informationList.size()];
            for (int i = 0; i < informationList.size(); i++) {
                Information information = informationList.get(i);
                id[i] = information.getInformationId();
                image[i] = new InformationUtil().getFirstImage(information);
                title[i] = information.getInformationTopic();
                time[i] = format.format(information.getInformationReltime());
            }

            map.put("id",id);
            map.put("image",image);
            map.put("title",title);
            map.put("time",time);
            return map;
        }else {
            return null;
        }
    }
}
