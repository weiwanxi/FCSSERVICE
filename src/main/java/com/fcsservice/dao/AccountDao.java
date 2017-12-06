package com.fcsservice.dao;

import com.fcsservice.model.dao.UserAccountMapper;
import com.fcsservice.model.pojo.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by YE on 2017/10/18.
 */

@Repository
public class AccountDao {
    @Autowired
    UserAccountMapper userAccountMapper;

    public UserAccount getUserAccountByAccount(String user_account){
        return userAccountMapper.selectByUserAccount(user_account);
    }

    public UserAccount getUserAccountById(String user_id){
        return userAccountMapper.selectByPrimaryKey(user_id);
    }

    public void addUserAccount(UserAccount userAccount){
        userAccountMapper.insert(userAccount);
    }

    public List<UserAccount> getAccountBySearch(String searchText, int page, int number){
        return userAccountMapper.selectAccountBySearch(searchText,page,number);
    }

    public void updatePassword(UserAccount userAccount){
        userAccountMapper.updateByPrimaryKeySelective(userAccount);
    }
}
