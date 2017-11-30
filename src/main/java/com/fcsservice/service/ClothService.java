package com.fcsservice.service;

import com.fcsservice.dao.*;
import com.fcsservice.model.pojo.Cloth;
import com.fcsservice.model.pojo.Costume;
import com.fcsservice.model.pojo.Work;
import com.fcsservice.utils.FcsserviceUtil;
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
 * Created by YE on 2017/11/19 15:35.
 */

@Service
@Transactional
public class ClothService {
    @Autowired
    ClothDao clothDao;
    @Autowired
    CostumeDao costumeDao;
    @Autowired
    WorkDao workDao;
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

    public Map<String,String[]> getClothOrderByComment(int page, int number){
        List<Cloth> clothList = clothDao.getClothOrderByComment(page,number);

        return getMap(clothList,number);
    }

    public Map<String,String[]> getClothOrderByFabulous(int page, int number){
        List<Cloth> clothList = clothDao.getClothOrderByFabulous(page,number);

        return getMap(clothList,number);
    }

    public Result getClothById(String userId, String clothId){
        Result result = new Result();
        Map<String,String[]> carouselMap = new HashMap<String, String[]>();
        Map<String,String> inforMap = new HashMap<String, String>();
        Map<String,String[]> recommendMap = new HashMap<String, String[]>();


        Cloth cloth = clothDao.getClothById(clothId);
        if (cloth != null){

            String clothComponent = componentDao.getComponentById(cloth.getComponentId()).getComponentName();
            String clothPurpose = dictdataDao.getDictDataById(cloth.getPurposeId()).getDictionarydataValue();
            String clothWeave = weaveDao.getWeaveById(cloth.getWeaveId()).getWeaveName();
            String clothMake = dictdataDao.getDictDataById(cloth.getMakeId()).getDictionarydataValue();
            String fabulousNumber = fabulousDao.getFabulous(clothId)+"";
            String commentNumber = commentDao.getCommentNumber(clothId)+"";

            //服装轮播图
            String[] carouse = new String[4];
            carouse[0] = cloth.getClothPicture1();
            carouse[1] = cloth.getClothPicture1();
            carouse[2] = cloth.getClothPicture1();
            carouse[3] = cloth.getClothPicture1();
            carouselMap.put("carouse",carouse);

            //布料详情
            inforMap.put("clothName",cloth.getClothName());
            inforMap.put("clothSupplier",cloth.getClothSupplier());
            inforMap.put("clothPrice",cloth.getClothPrice()+"");
            inforMap.put("clothNo",cloth.getClothNo());
            inforMap.put("clothMoq",cloth.getClothMoq()+"");
            inforMap.put("clothComponent",clothComponent);
            inforMap.put("clothPurpose",clothPurpose);
            inforMap.put("clothWeave",clothWeave);
            inforMap.put("clothMake",clothMake);
            inforMap.put("clothElastic",cloth.getClothElastic()==0?"有":"无");
            inforMap.put("clothTcx",cloth.getClothTcx());
            inforMap.put("clothTpx",cloth.getClothTpx());
            inforMap.put("fabulousNumber",fabulousNumber);
            inforMap.put("commentNumber",commentNumber);
            inforMap.put("collectType",FcsserviceUtil.CollectFalse);
            inforMap.put("fabulousType",FcsserviceUtil.FabulousFalse);
            if (userId != null){
                String collectType = collectDao.getCollectType(clothId,userId)? FcsserviceUtil.CollectTrue:FcsserviceUtil.CollectFalse;
                inforMap.put("collectType",collectType);
                String fabulousType = fabulousDao.getFabulousType(clothId,userId)? FcsserviceUtil.FabulousTrue:FcsserviceUtil.FabulousFalse;
                inforMap.put("fabulousType",fabulousType);
            }

            //推荐信息
            List<Work> workList = workDao.getWorkOrderByFabulous(0,4);
            List<Costume> costumeList = costumeDao.getCostumeOrderByFabulous(0,4);
            Map<String,String[]> workMap = getWorkMap(workList,4);
            Map<String,String[]> costumeMap = getCostumeMap(costumeList,4);
            if (workMap != null && costumeMap != null){
                workMap.putAll(costumeMap);
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

    private Map<String,String[]> getCostumeMap(List<Costume> costumeList, int number){
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
            map.put("costumeIdArray",id);
            map.put("costumeImageArray",image);
            map.put("costumeTitleArray",title);
            map.put("costumeAboutArray",time);
            map.put("costumeFabulousArray",fabulous);
        }else {
            return null;
        }
        return map;
    }
}
