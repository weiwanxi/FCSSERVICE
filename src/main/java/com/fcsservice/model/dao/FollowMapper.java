package com.fcsservice.model.dao;

import com.fcsservice.model.pojo.Follow;

public interface FollowMapper {
    int deleteByPrimaryKey(String followId);

    int deleteByUCId(String userId,String concernId);

    int insert(Follow record);

    int insertSelective(Follow record);

    Follow selectByPrimaryKey(String followId);

    Follow selectFollowed(String userId,String designerId);

    int updateByPrimaryKeySelective(Follow record);

    int updateByPrimaryKey(Follow record);
}