package com.fcsservice.service;

import com.fcsservice.dao.*;
import com.fcsservice.model.pojo.*;
import com.fcsservice.utils.FcsserviceUtil;
import com.fcsservice.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by YE on 2017/11/19 15:34.
 */

@Service
@Transactional
public class WorkService {
    @Autowired
    WorkDao workDao;
    @Autowired
    FabulousDao fabulousDao;
    @Autowired
    AccountDao accountDao;
    @Autowired
    ClothDao clothDao;
    @Autowired
    CostumeDao costumeDao;
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
    AlbumDao albumDao;

    public boolean deleteWork(String workId){
        Work work = workDao.getWorkById(workId);
        if (work != null){
            workDao.deleteWork(workId);
            return true;
        }else {
            return false;
        }
    }

    public boolean updateWork(String workId,String albumId,String designerId,String workName,String workColor,String workType,
                           String workComponent,String workStyle,String workModel,String workIntro,String[] workImageArray){
        Work work = workDao.getWorkById(workId);
        if (work != null){
            FcsserviceUtil fcsserviceUtil = new FcsserviceUtil();
            String[] workImageFileName = new String[6];
            work.setAlbumId(albumId);
            work.setDesignerId(designerId);
            work.setWorkName(workName);
            work.setTypeId(Integer.parseInt(workType));
            work.setComponentId(Integer.parseInt(workComponent));
            work.setStyleId(Integer.parseInt(workStyle));
            work.setModelId(Integer.parseInt(workModel));
            work.setWorkColor(workColor);
            work.setWorkIntro(workIntro);
            work.setWorkReltime(new Date());
            work.setWorkStatus(0);

            //删除原有作品图片
            File pictureFile1 = new File(fcsserviceUtil.IMAGE1PATH +work.getWorkPicture1());
            File pictureFile2 = new File(fcsserviceUtil.IMAGE1PATH +work.getWorkPicture2());
            File pictureFile3 = new File(fcsserviceUtil.IMAGE1PATH +work.getWorkPicture3());
            File pictureFile4 = new File(fcsserviceUtil.IMAGE1PATH +work.getWorkPicture4());
            File pictureFile5 = new File(fcsserviceUtil.IMAGE1PATH +work.getWorkPicture5());
            File pictureFile6 = new File(fcsserviceUtil.IMAGE1PATH +work.getWorkPicture6());
            if (pictureFile1.isFile() && pictureFile1.exists()) {
                pictureFile1.delete();
            }
            if (pictureFile2.isFile() && pictureFile2.exists()) {
                pictureFile2.delete();
            }
            if (pictureFile3.isFile() && pictureFile3.exists()) {
                pictureFile3.delete();
            }
            if (pictureFile4.isFile() && pictureFile4.exists()) {
                pictureFile4.delete();
            }
            if (pictureFile5.isFile() && pictureFile5.exists()) {
                pictureFile5.delete();
            }
            if (pictureFile6.isFile() && pictureFile6.exists()) {
                pictureFile6.delete();
            }
            //添加新的图片
            for (int i = 0; i < workImageArray.length; i++) {
                String fileName = fcsserviceUtil.saveImage(fcsserviceUtil.IMAGE1PATH,workImageArray[i]);
                if (fileName == null){
                    return false;
                }
                workImageFileName[i] = fileName;
            }
            work.setWorkPicture1(workImageFileName[0]);
            work.setWorkPicture2(workImageFileName[1]);
            work.setWorkPicture3(workImageFileName[2]);
            work.setWorkPicture4(workImageFileName[3]);
            work.setWorkPicture5(workImageFileName[4]);
            work.setWorkPicture6(workImageFileName[5]);

            workDao.updateWork(work);
        }else {
            return false;
        }

        return true;
    }

    public boolean addWork(String albumId,String designerId,String workName,String workColor,String workType,
                           String workComponent,String workStyle,String workModel,String workIntro,String[] workImageArray){
        Work work = new Work();
        FcsserviceUtil fcsserviceUtil = new FcsserviceUtil();
        String[] workImageFileName = new String[6];
        String workId = UUID.randomUUID().toString().replaceAll("-", "");
        work.setWorkId(workId);
        work.setAlbumId(albumId);
        work.setDesignerId(designerId);
        work.setWorkName(workName);
        work.setTypeId(Integer.parseInt(workType));
        work.setComponentId(Integer.parseInt(workComponent));
        work.setStyleId(Integer.parseInt(workStyle));
        work.setModelId(Integer.parseInt(workModel));
        work.setWorkColor(workColor);
        work.setWorkIntro(workIntro);
        work.setWorkReltime(new Date());
        work.setWorkStatus(0);
        for (int i = 0; i < workImageArray.length; i++) {
            String fileName = fcsserviceUtil.saveImage(fcsserviceUtil.IMAGE1PATH,workImageArray[i]);
            if (fileName == null){
                return false;
            }
            workImageFileName[i] = fileName;
        }
        work.setWorkPicture1(workImageFileName[0]);
        work.setWorkPicture2(workImageFileName[1]);
        work.setWorkPicture3(workImageFileName[2]);
        work.setWorkPicture4(workImageFileName[3]);
        work.setWorkPicture5(workImageFileName[4]);
        work.setWorkPicture6(workImageFileName[5]);

        workDao.addWork(work);
        return true;
    }

    public Map<String,String[]> getWorkOrderByComment(int page, int number){
        List<Work> workList = workDao.getWorkOrderByComment(page,number);

        return getMap(workList,number);
    }

    public Map<String,String[]> getWorkOrderByFabulous(int page, int number){
        List<Work> workList = workDao.getWorkOrderByFabulous(page,number);

        return getMap(workList,number);
    }

    public Map<String,String[]> getWorkListByAlbumId(String albumId){
        List<Work> workList = workDao.getWorkListByAlbumId(albumId);

        if (workList != null)
            return getMap(workList);
        else
            return null;
    }

    public Result getWorkById(String userId, String workId){
        Result result = new Result();
        Map<String,String[]> carouselMap = new HashMap<String, String[]>();
        Map<String,String> inforMap = new HashMap<String, String>();
        Map<String,String[]> recommendMap = new HashMap<String, String[]>();

        Work work = workDao.getWorkById(workId);
        if (work != null){
            String workType = costumeTypeDao.getCostumeTypeById(work.getTypeId()).getTypeName();
            String workStyle = dictdataDao.getDictDataById(work.getStyleId()).getDictionarydataValue();
            String workAlbum = albumDao.getALbumById(work.getAlbumId()).getAlbumName();
            String workComponent = componentDao.getComponentById(work.getComponentId()).getComponentName();
            String workModel = dictdataDao.getDictDataById(work.getModelId()).getDictionarydataValue();
            String fabulousNumber = fabulousDao.getFabulous(workId)+"";
            String commentNumber = commentDao.getCommentNumber(workId)+"";
            String designerName = accountDao.getUserAccountById(work.getDesignerId()).getUserAccount();

            //获取轮播图
            String[] carouse = new String[6];
            carouse[0] = work.getWorkPicture1();
            carouse[1] = work.getWorkPicture2();
            carouse[2] = work.getWorkPicture3();
            carouse[3] = work.getWorkPicture4();
            carouse[4] = work.getWorkPicture5();
            carouse[5] = work.getWorkPicture6();
            carouselMap.put("carouse",carouse);

            //作品信息
            inforMap.put("workName",work.getWorkName());
            inforMap.put("workDesigner",designerName);
            inforMap.put("workDesignerId",work.getDesignerId());
            inforMap.put("workAlbum",workAlbum);
            inforMap.put("workAlbumId",work.getAlbumId());
            inforMap.put("workIntro",work.getWorkIntro());
            inforMap.put("workType",workType);
            inforMap.put("workStyle",workStyle);
            inforMap.put("workComponent",workComponent);
            inforMap.put("workModel",workModel);
            inforMap.put("workColor",work.getWorkColor());
            inforMap.put("fabulousNumber",fabulousNumber);
            inforMap.put("commentNumber",commentNumber);
            inforMap.put("collectType", FcsserviceUtil.CollectFalse);
            inforMap.put("fabulousType",FcsserviceUtil.FabulousFalse);
            if (userId != null){
                String collectType = collectDao.getCollectType(workId,userId)? FcsserviceUtil.CollectTrue:FcsserviceUtil.CollectFalse;
                inforMap.put("collectType",collectType);
                String fabulousType = fabulousDao.getFabulousType(workId,userId)? FcsserviceUtil.FabulousTrue:FcsserviceUtil.FabulousFalse;
                inforMap.put("fabulousType",fabulousType);
            }

            //推荐信息
            List<Cloth> clothList = clothDao.getClothOrderByFabulous(0,4);
            List<Costume> costumeList = costumeDao.getCostumeOrderByFabulous(0,4);
            Map<String,String[]> clothMap = getClothMap(clothList,4);
            Map<String,String[]> costumeMap = getCostumeMap(costumeList,4);
            if (clothMap != null && costumeMap != null){
                clothMap.putAll(costumeMap);
                recommendMap.putAll(clothMap);
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

    public Map<String,String> getWorkToUpdate(String workId){
        Map<String,String> map = new HashMap<String, String>();
        Work work = workDao.getWorkById(workId);
        if (work != null){
            map.put("workName",work.getWorkName());
            map.put("workColor",work.getWorkColor());
            map.put("workIntro",work.getWorkIntro());

            map.put("workPicture1",work.getWorkPicture1());
            map.put("workPicture2",work.getWorkPicture2());
            map.put("workPicture3",work.getWorkPicture3());
            map.put("workPicture4",work.getWorkPicture4());
            map.put("workPicture5",work.getWorkPicture5());
            map.put("workPicture6",work.getWorkPicture6());

            //作品分类
            CostumeType costumeType = costumeTypeDao.getCostumeTypeById(work.getTypeId());
            if (costumeType != null){
                map.put("costumeTypeMal",costumeType.getTypeName());
                map.put("costumeTypeMalValue",costumeType.getTypeId()+"");
                costumeType = costumeTypeDao.getCostumeTypeById(costumeType.getTypeSupcategpry());
                if (costumeType != null){
                    map.put("costumeTypeMid",costumeType.getTypeName());
                    costumeType = costumeTypeDao.getCostumeTypeById(costumeType.getTypeSupcategpry());
                    if (costumeType != null){
                        map.put("costumeTypeBig",costumeType.getTypeName());
                    }else {
                        return null;
                    }
                }else {
                    return null;
                }
            }else {
                return null;
            }
            //作品面料
            Component component = componentDao.getComponentById(work.getComponentId());
            if (component != null){
                map.put("componentMal",component.getComponentName());
                map.put("componentMalValue",costumeType.getTypeId()+"");
                component = componentDao.getComponentById(component.getComponentSupcategory());
                if (component != null){
                    map.put("componentBig",component.getComponentName());
                }else {
                    return null;
                }
            }else {
                return null;
            }
            //作品风格
            Dictdata dictdata = dictdataDao.getDictDataById(work.getStyleId());
            if (dictdata!=null){
                map.put("workStyle",dictdata.getDictionarydataValue());
                map.put("workStyleValue",dictdata.getDactionarydataId()+"");
            }else {
                return null;
            }
            //作品款式
            dictdata = dictdataDao.getDictDataById(work.getModelId());
            if (dictdata!=null){
                map.put("workModel",dictdata.getDictionarydataValue());
                map.put("workModelValue",dictdata.getDactionarydataId()+"");
            }else {
                return null;
            }
        }else {
            return null;
        }
        return map;
    }

    private Map<String,String[]> getMap(List<Work> workList,int number){
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
            map.put("id",id);
            map.put("image",image);
            map.put("title",title);
            map.put("designer",designer);
            map.put("fabulous",fabulous);
        }else {
            return null;
        }
        return map;
    }

    private Map<String,String[]> getMap(List<Work> workList){
        int size = workList.size();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String,String[]> map = new HashMap<String, String[]>();
        String[] id = new String[size];
        String[] image = new String[size];
        String[] title = new String[size];
        String[] time = new String[size];
        String[] fabulous = new String[size];
        String[] comment = new String[size];

        for (int i=0;i<size;i++){
            id[i] = workList.get(i).getWorkId();
            image[i] = workList.get(i).getWorkPicture1();
            title[i] = workList.get(i).getWorkName();
            time[i] = format.format(workList.get(i).getWorkReltime());
            fabulous[i] = fabulousDao.getFabulous(workList.get(i).getWorkId())+"";
            comment[i] = commentDao.getCommentNumber(workList.get(i).getWorkId())+"";
        }
        map.put("id",id);
        map.put("img",image);
        map.put("title",title);
        map.put("time",time);
        map.put("fabulous",fabulous);
        map.put("comment",comment);
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
