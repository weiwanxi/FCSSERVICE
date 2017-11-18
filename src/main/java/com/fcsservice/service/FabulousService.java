package com.fcsservice.service;

import com.fcsservice.dao.AccountDao;
import com.fcsservice.dao.FabulousDao;
import com.fcsservice.dao.InformationDao;
import com.fcsservice.model.pojo.Fabulous;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Created by YE on 2017/11/17 23:53.
 */

@Service
@Transactional
public class FabulousService {
    @Autowired
    FabulousDao fabulousDao;
    @Autowired
    AccountDao accountDao;
    @Autowired
    InformationDao informationDao;

    public String addFabulous(String informationId,String userId){
        Fabulous fabulous = new Fabulous();

        if (accountDao.getUserAccountById(userId) == null) {
            return "该用户不存在";
        }else if (informationDao.getInformationById(informationId) == null){
            return "该信息不存在";
        }

        fabulous.setFabulousId(UUID.randomUUID().toString().replaceAll("-", ""));
        fabulous.setInformationId(informationId);
        fabulous.setUserId(userId);

        fabulousDao.addFabulous(fabulous);
        return null;
    }

    public String deleteFabulous(String informationId,String userId){
        if (accountDao.getUserAccountById(userId) == null) {
            return "该用户不存在";
        }else if (informationDao.getInformationById(informationId) == null){
            return "该信息不存在";
        }

        Fabulous fabulous = fabulousDao.getFabulousByIU(informationId,userId);
        if (fabulous == null){
            return "该点赞不存在";
        }else {
            fabulousDao.deleteFabulous(fabulous.getFabulousId());
        }

        return null;
    }
}
