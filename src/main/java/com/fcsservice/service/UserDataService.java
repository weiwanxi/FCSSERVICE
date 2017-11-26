package com.fcsservice.service;

import com.fcsservice.dao.AccountDao;
import com.fcsservice.dao.UserDataDao;
import com.fcsservice.model.pojo.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Created by YE on 2017/10/26 15:44.
 */

@Service
@Transactional
public class UserDataService {
    @Autowired
    UserDataDao userDataDao;
    @Autowired
    AccountDao accountDao;

    public boolean existMail(String mail){
        UserData userData = userDataDao.getDataByMail(mail);
        return userData != null;
    }

    public String getUserId(String mail){
        UserData userData = userDataDao.getDataByMail(mail);
        if (userData != null)
            return userData.getUserId();
        else
            return null;
    }

    public UserData getUserByMail(String mail){
        UserData userData = userDataDao.getDataByMail(mail);
        if (userData != null)
            return userData;
        else
            return null;
    }

    public UserData getUserDataByUserId(String userId){
        return userDataDao.getUserDataByUserId(userId);
    }

    public void addUserPortrait(String userId,String fileName){
        UserData userData = userDataDao.getUserDataByUserId(userId);
        userData.setDataPortrait(fileName);
        userDataDao.updateUserData(userData);
    }

    public void updateUserData(UserData userData){
        userDataDao.updateUserData(userData);
    }
}
