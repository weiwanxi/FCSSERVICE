package com.fcsservice.service;

import com.fcsservice.dao.UserDataDao;
import com.fcsservice.model.pojo.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by YE on 2017/10/26 15:44.
 */

@Service
@Transactional
public class UserDataService {
    @Autowired
    UserDataDao userDataDao;

    public boolean existMail(String mail){
        UserData userData = userDataDao.getDataByMail(mail);
        return userData != null;
    }
}
