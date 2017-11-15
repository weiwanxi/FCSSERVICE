package com.fcsservice.service;

import com.fcsservice.dao.AccountDao;
import com.fcsservice.dao.UserDataDao;
import com.fcsservice.model.pojo.UserAccount;
import com.fcsservice.model.pojo.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by YE on 2017/10/18.
 */

@Service
@Transactional
public class AccountService {
    @Autowired
    private AccountDao accountDao;
    @Autowired
    UserDataDao userDataDao;

    public String login(String user_account,String user_password){
        UserAccount userAccount = accountDao.getUserAccountByAccount(user_account);
        if (userAccount == null){
            UserData userData = userDataDao.getDataByMail(user_account);
            if (userData != null){
                userAccount = accountDao.getUserAccountById(userData.getUserId());
            }
        }

        if (userAccount == null){
            return  null;
        }else if (userAccount.getUserPassword().equals(user_password)) {
            return userAccount.getUserId();
        }else {
            return null;
        }
    }

    public boolean existUserAccount(String user_account){
        UserAccount userAccount = accountDao.getUserAccountByAccount(user_account);
        return userAccount==null;
    }

    public void addUserAccount(UserAccount userAccount){
        accountDao.addUserAccount(userAccount);
    }

    public void updatePassword(String userId,String password){
        UserAccount userAccount = new UserAccount();
        userAccount.setUserId(userId);
        userAccount.setUserPassword(password);

        accountDao.updatePassword(userAccount);
    }

    public UserAccount getAccountById(String userId){
        return accountDao.getUserAccountById(userId);
    }
}
