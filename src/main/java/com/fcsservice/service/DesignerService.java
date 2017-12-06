package com.fcsservice.service;

import com.fcsservice.dao.*;
import com.fcsservice.form.DesignerForm;
import com.fcsservice.model.pojo.UserAccount;
import com.fcsservice.model.pojo.UserData;
import com.fcsservice.utils.AlbumUtil;
import com.fcsservice.utils.DesignerUtil;
import com.fcsservice.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YE on 2017/11/15 9:42.
 */

@Service
@Transactional
public class DesignerService {
    @Autowired
    DesignerDao designerDao;
    @Autowired
    WorkDao workDao;
    @Autowired
    UserDataDao userDataDao;
    @Autowired
    AccountDao accountDao;
    @Autowired
    FollowDao followDao;
    @Autowired
    AlbumDao albumDao;
    @Autowired
    TagDao tagDao;

    private Map<String,List<DesignerForm>> userMap = new HashMap<String, List<DesignerForm>>();

    public Map<String,String[]> getDesignerList(String userId,String lastDesignerId){
        List<DesignerForm> designerFormList;
        List<DesignerForm> designerForms;
        if (lastDesignerId == null || "null".equals(lastDesignerId)) {
            userMap.remove(userId);
            designerFormList = null;
            designerForms = workDao.getDesignerWorkNumber();
        }else {
            int minNumber = workDao.getWorkNumberByDersignerId(lastDesignerId);
            designerFormList = userMap.get(userId);
            designerForms = workDao.getDesignerWorkNumberByNumber(minNumber);
        }

        for (int i = 0; i < designerForms.size(); i++) {
            DesignerForm designerForm = designerForms.get(i);

            UserData userData = userDataDao.getUserDataByUserId(designerForm.getDesignerId());
            UserAccount account = accountDao.getUserAccountById(designerForm.getDesignerId());
            String follow = followDao.getFollowType(userId, designerForm.getDesignerId());
            if (userData != null && account != null) {
                designerForm.setDesignerPortrait(userData.getDataPortrait());
                designerForm.setDesignerName(account.getUserAccount());
                designerForm.setFollow(follow);
            } else {
                return null;
            }
        }

        designerForms = new DesignerUtil().getDesignerNotRepetition(designerFormList,designerForms);

        if (designerFormList == null)
            designerFormList = new ArrayList<DesignerForm>();

        designerFormList.addAll(designerForms);
        userMap.put(userId,designerFormList);

        return new DesignerUtil().getDesignerMap(designerForms);
    }

    public Map<String,String[]> getDesignerListByTag(String userId,String[] tagArray){
        List<String> designerIdList = new ArrayList<String>();
        for (int i = 0; i < tagArray.length; i++) {
            String tagId = tagArray[i];
            List<String> designerIdListTemp = tagDao.getUserIdListByTag(tagId);
            if (i==0)
                designerIdList = designerIdListTemp;
            else
                designerIdList = new DesignerUtil().getRepetition(designerIdList,designerIdListTemp);
        }

        List<DesignerForm> designerForms = new ArrayList<DesignerForm>();
        for (int i = 0; i < designerIdList.size(); i++) {
            String designerId = designerIdList.get(i);
            DesignerForm designerForm = new DesignerForm();
            designerForm.setDesignerId(designerId);
            designerForm.setDesignerName(accountDao.getUserAccountById(designerId).getUserAccount());
            designerForm.setDesignerPortrait(userDataDao.getUserDataByUserId(designerId).getDataPortrait());
            designerForm.setWorkNumber(workDao.getWorkNumberByDersignerId(designerId));
            designerForm.setFollow(followDao.getFollowType(userId,designerId));
            designerForms.add(designerForm);
        }
        return new DesignerUtil().getDesignerMap(designerForms);
    }

    public Result getDeisgner(String designerId, String userId){
        Result result = new Result();

        Map<String,String> designerInfo = new HashMap<String, String>();
        //获取设计师用户名
        UserAccount userAccount = accountDao.getUserAccountById(designerId);
        if (userAccount == null){
            result.setCode(Result.FAIL);
            result.setMsg("该设计师不存在");
            return result;
        }else {
            designerInfo.put("designerName",userAccount.getUserAccount());
        }
        //获取设计头像
        UserData userData = userDataDao.getUserDataByUserId(designerId);
        if (userData == null){
            result.setCode(Result.FAIL);
            result.setMsg("该设计师不存在");
            return result;
        }else {
            designerInfo.put("designerPortrait",userData.getDataPortrait());
        }
        //获取设计师关注数量
        designerInfo.put("designerFollow",followDao.getFollowNumber(designerId)+"");
        //获取设计师粉丝数量
        designerInfo.put("designerFans",followDao.getFansNumber(designerId)+"");
        //获取是否已关注设计师
        designerInfo.put("followType",followDao.getFollowType(userId,designerId));

        //获取设计专辑
        Map<String,String[]> albumMap = new AlbumUtil().getAlbum(albumDao.getUserAlbum(designerId));
        String[] idArray = albumMap.get("albumId");
        String[] numberArray = new String[idArray.length];
        for (int i = 0; i < idArray.length; i++) {
            numberArray[i] = workDao.getWorkNumberByAlbumId(idArray[i])+"";
        }
        albumMap.put("workNumber",numberArray);

        result.setCode(Result.SUCCESS);
        result.setObj(designerInfo);
        result.setObj1(albumMap);
        return result;
    }

    public Map<String,String[]> getDesignerBySearch(String searchText,int page,int number){
        Map<String,String[]> map = new HashMap<String, String[]>();
        List<UserAccount> accountList = accountDao.getAccountBySearch(searchText,page,number);
        if (accountList != null){
            String[] id = new String[accountList.size()];
            String[] image = new String[accountList.size()];
            String[] title = new String[accountList.size()];
            String[] time = new String[accountList.size()];
            for (int i = 0; i < accountList.size(); i++) {
                UserAccount account = accountList.get(i);
                id[i] = account.getUserId();
                image[i] = userDataDao.getUserDataByUserId(account.getUserId()).getDataPortrait();
                title[i] = account.getUserAccount();
                time[i] = "作品数量："+workDao.getWorkNumberByDersignerId(account.getUserId());
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