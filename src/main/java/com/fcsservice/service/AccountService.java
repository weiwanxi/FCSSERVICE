package com.fcsservice.service;

import com.fcsservice.dao.AccountDao;
import com.fcsservice.model.pojo.UserAccount;
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

    public String login(String user_account,String user_password){
        UserAccount userAccount = accountDao.getUserAccount(user_account);
        if (userAccount == null){
            return null;
        }else if (userAccount.getUserPassword().equals(user_password)) {
            return userAccount.getUserId();
        }else {
            return null;
        }
    }

    public boolean existUserAccount(String user_account){
        UserAccount userAccount = accountDao.getUserAccount(user_account);
        return userAccount==null?false:true;
    }

    public void addUserAccount(UserAccount userAccount){
        accountDao.addUserAccount(userAccount);
    }

    public int[] getTagidList(int[] tag){
        return null;
    }
}
