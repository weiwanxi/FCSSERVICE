package com.fcsservice.service;

import com.fcsservice.dao.AccountDao;
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

    public boolean validate(String user_account,String user_password){
        String correct_password = accountDao.getPassword(user_account);
        return user_password.equals(correct_password);
    }
}
