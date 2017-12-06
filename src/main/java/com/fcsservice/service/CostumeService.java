package com.fcsservice.service;

import com.fcsservice.dao.*;
import com.fcsservice.model.pojo.*;
import com.fcsservice.utils.FcsserviceUtil;
import com.fcsservice.utils.Result;
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
    WorkDao workDao;
    @Autowired
    ClothDao clothDao;
    @Autowired
    FabulousDao fabulousDao;
    @Autowired
    CommentDao commentDao;
    @Autowired
    CollectDao collectDao;
    @Autowired
    CostumeTypeDao costumeTypeDao;
    @Autowired
    ComponentDao componentDao;
    @Autowired
    WeaveDao weaveDao;
    @Autowired
    DictdataDao dictdataDao;
    @Autowired
    AccountDao accountDao;

    public Map<String,String[]> getCostumeOrderByComment(int page, int number,int screen){
        List<Costume> costumeList = costumeDao.getCostumeOrderByComment(page,number,screen);

        return getMap(costumeList,number);
    }

    public Map<String,String[]> getCostumeOrderByFabulous(int page, int number,int screen){
        List<Costume> costumeList = costumeDao.getCostumeOrderByFabulous(page,number,screen);

        return getMap(costumeList,number);
    }

    public Result getCostumeById(String userId,String costumeId){
        Result result = new Result();
        Map<String,String[]> carouselMap = new HashMap<String, String[]>();
        Map<String,String> inforMap = new HashMap<String, String>();
        Map<String,String[]> recommendMap = new HashMap<String, String[]>();


        Costume costume = costumeDao.getCostumeById(costumeId);
        if (costume != null){

            String costumeType = costumeTypeDao.getCostumeTypeById(costume.getTypeId()).getTypeName();
            String costumeModel = dictdataDao.getDictDataById(costume.getModelId()).getDictionarydataValue();
            String costumeStyle = dictdataDao.getDictDataById(costume.getStyleId()).getDictionarydataValue();
            String costumeComponent = componentDao.getComponentById(costume.getComponentId()).getComponentName();
            String costumeWeave = weaveDao.getWeaveById(costume.getWeaveId()).getWeaveName();
            String fabulousNumber = fabulousDao.getFabulous(costumeId)+"";
            String commentNumber = commentDao.getCommentNumber(costumeId)+"";

            //服装轮播图
            String[] carouse = new String[4];
            carouse[0] = costume.getCostumePicture1();
            carouse[1] = costume.getCostumePicture2();
            carouse[2] = costume.getCostumePicture3();
            carouse[3] = costume.getCostumePicture4();
            carouselMap.put("carouse",carouse);
            //服装详情
            inforMap.put("costumeName",costume.getCostumeName());
            inforMap.put("costumeNo",costume.getCostumeNo());
            inforMap.put("costumeIntr",costume.getCostumeIntro());
            inforMap.put("costumeType",costumeType);
            inforMap.put("costumeModel",costumeModel);
            inforMap.put("costumeStyle",costumeStyle);
            inforMap.put("costumeComponent",costumeComponent);
            inforMap.put("costumeWeave",costumeWeave);
            inforMap.put("costumeColor",costume.getCostumeColor());
            inforMap.put("costumeAge",costume.getCostumeAge());
            inforMap.put("costumeSeason",costume.getCostumeSeason());
            inforMap.put("fabulousNumber",fabulousNumber);
            inforMap.put("commentNumber",commentNumber);
            inforMap.put("collectType",FcsserviceUtil.CollectFalse);
            inforMap.put("fabulousType",FcsserviceUtil.FabulousFalse);
            if (userId != null){
                String collectType = collectDao.getCollectType(costumeId,userId)? FcsserviceUtil.CollectTrue:FcsserviceUtil.CollectFalse;
                inforMap.put("collectType",collectType);
                String fabulousType = fabulousDao.getFabulousType(costumeId,userId)? FcsserviceUtil.FabulousTrue:FcsserviceUtil.FabulousFalse;
                inforMap.put("fabulousType",fabulousType);
            }
            //推荐信息
            List<Work> workList = workDao.getWorkOrderByFabulous(0,4,-1);
            List<Cloth> clothList = clothDao.getClothOrderByFabulous(0,4,-1);
            Map<String,String[]> workMap = getWorkMap(workList,4);
            Map<String,String[]> clothMap = getClothMap(clothList,4);
            if (workMap != null && clothMap != null){
                workMap.putAll(clothMap);
                recommendMap.putAll(workMap);
            }

            result.setCode(Result.SUCCESS);
            result.setObj(carouselMap);
            result.setObj1(inforMap);
            result.setObj2(recommendMap);
            result.setMsg("成功数据");
        }else {
            result.setCode(Result.FAIL);
            result.setMsg("获取数据出错");
        }

        return result;
    }

    public Map<String,String[]> getCostumeBySearch(String searchText,int page,int number){
        Map<String,String[]> map = new HashMap<String, String[]>();
        List<Costume> costumeList = costumeDao.getCostumeBySearch(searchText,page,number);
        if (costumeList != null){
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String[] id = new String[costumeList.size()];
            String[] image = new String[costumeList.size()];
            String[] title = new String[costumeList.size()];
            String[] time = new String[costumeList.size()];
            for (int i = 0; i < costumeList.size(); i++) {
                Costume costume = costumeList.get(i);
                id[i] = costume.getCostumeId();
                image[i] = costume.getCostumePicture1();
                title[i] = costume.getCostumeName();
                time[i] = format.format(costume.getCostumeReltime());
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

    private Map<String,String[]> getWorkMap(List<Work> workList, int number){
        Map<String,String[]> map = new HashMap<String, String[]>();
        String[] id = new String[number];
        String[] image = new String[number];
        String[] title = new String[number];
        String[] designer = new String[number];
        String[] fabulous = new String[number];

        if (workList != null){
            for (int i=0;i<workList.size();i++){
                id[i] = workList.get(i).getWorkId();
                image[i] = workList.get(i).getWorkPicture1();
                title[i] = workList.get(i).getWorkName();
                designer[i] = accountDao.getUserAccountById(workList.get(i).getDesignerId()).getUserAccount();
                fabulous[i] = fabulousDao.getFabulous(workList.get(i).getWorkId())+"";
            }
            map.put("workIdArray",id);
            map.put("workImageArray",image);
            map.put("workTitleArray",title);
            map.put("workAboutArray",designer);
            map.put("workFabulousArray",fabulous);
        }else {
            return null;
        }
        return map;
    }

    private Map<String,String[]> getClothMap(List<Cloth> clothList, int number){
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
            map.put("clothIdArray",id);
            map.put("clothImageArray",image);
            map.put("clothTitleArray",title);
            map.put("clothAboutArray",time);
            map.put("clothFabulousArray",fabulous);
        }else {
            return null;
        }
        return map;
    }
}
