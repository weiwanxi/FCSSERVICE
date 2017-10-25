package com.fcsservice.model.dao;

import com.fcsservice.model.pojo.UserData;

public interface UserDataMapper {
    int deleteByPrimaryKey(String dataId);

    int insert(UserData record);

    int insertSelective(UserData record);

    UserData selectByPrimaryKey(String dataId);

    int updateByPrimaryKeySelective(UserData record);

    int updateByPrimaryKey(UserData record);
}