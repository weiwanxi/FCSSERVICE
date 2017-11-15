package com.fcsservice.dao;

import com.fcsservice.model.dao.UserDataMapper;
import com.fcsservice.model.pojo.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by YE on 2017/10/26 15:45.
 */

@Repository
public class UserDataDao {
    @Autowired
    UserDataMapper userDataMapper;

    public UserData getDataByMail(String mail){
        return userDataMapper.selectByMail(mail);
    }

    public void addUserData(UserData userData){
        userDataMapper.insertSelective(userData);
    }

    public UserData getUserDataByUserId(String userId){
        return userDataMapper.selectByUserId(userId);
    }

    public void updateUserData(UserData userData){
        userDataMapper.updateByPrimaryKeySelective(userData);
    }
}
