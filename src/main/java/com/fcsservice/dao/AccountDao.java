package com.fcsservice.dao;

import com.fcsservice.model.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by YE on 2017/10/18.
 */

@Repository
public class AccountDao {
    @Autowired
    AccountMapper accountMapper;

    public String getPassword(String user_account){
        return accountMapper.getPassword(user_account);
    }
}
