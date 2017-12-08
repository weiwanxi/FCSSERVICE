package com.fcsservice.model.dto;

import com.fcsservice.model.pojo.UserAccount;

import java.util.List;

public interface UserAccountMapper {
    int deleteByPrimaryKey(String userId);

    int insert(UserAccount record);

    int insertSelective(UserAccount record);

    UserAccount selectByPrimaryKey(String userId);

    UserAccount selectByUserAccount(String user_account);

    List<UserAccount> selectAccountBySearch(String searchText,int page,int number);

    List<UserAccount> selectAllDesigner(int page,int number);

    int updateByPrimaryKeySelective(UserAccount record);

    int updateByPrimaryKey(UserAccount record);
}