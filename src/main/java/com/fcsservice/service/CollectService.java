package com.fcsservice.service;

import com.fcsservice.dao.AccountDao;
import com.fcsservice.dao.CollectDao;
import com.fcsservice.dao.InformationDao;
import com.fcsservice.model.pojo.Collect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

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

    public String addCollect(String informationId,String userId,int collectType){
        Collect collect = new Collect();
        if (accountDao.getUserAccountById(userId) == null) {
            return "该用户不存在";
        }else if (informationDao.getInformationById(informationId) == null){
            return "该信息不存在";
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
        }else if (informationDao.getInformationById(informationId) == null){
            return "该信息不存在";
        }

        Collect collect = collectDao.getCollectByIU(informationId,userId);
        if (collect == null){
            return "该收藏不存在";
        }else {
            collectDao.deleteCollect(collect.getCollectId());
        }

        return null;
    }
}
