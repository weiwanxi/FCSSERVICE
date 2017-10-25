package com.fcsservice.model.dao;

import com.fcsservice.model.pojo.UserAccount;

public interface UserAccountMapper {
    int deleteByPrimaryKey(String userId);

    int insert(UserAccount record);

    int insertSelective(UserAccount record);

    UserAccount selectByPrimaryKey(String userId);
    
    UserAccount selectByUserAccount(String user_account);

    int updateByPrimaryKeySelective(UserAccount record);

    int updateByPrimaryKey(UserAccount record);
}