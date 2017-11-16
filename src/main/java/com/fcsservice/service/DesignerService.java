package com.fcsservice.service;

import com.fcsservice.dao.*;
import com.fcsservice.form.DesignerForm;
import com.fcsservice.model.pojo.UserAccount;
import com.fcsservice.model.pojo.UserData;
import com.fcsservice.utils.DesignerUtil;
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
    TagDao tagDao;
    @Autowired
    DictdataDao dictdataDao;

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
}
