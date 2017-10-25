package com.fcsservice.model.dao;

import com.fcsservice.model.pojo.Follow;

public interface FollowMapper {
    int deleteByPrimaryKey(String followId);

    int insert(Follow record);

    int insertSelective(Follow record);

    Follow selectByPrimaryKey(String followId);

    int updateByPrimaryKeySelective(Follow record);

    int updateByPrimaryKey(Follow record);
}