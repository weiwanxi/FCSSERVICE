package com.fcsservice.model.dao;

import com.fcsservice.model.pojo.Follow;

import java.util.List;

public interface FollowMapper {
    int deleteByPrimaryKey(String followId);

    int deleteByUCId(String userId,String concernId);

    int insert(Follow record);

    int insertSelective(Follow record);

    Follow selectByPrimaryKey(String followId);

    Follow selectFollowed(String userId,String designerId);

    List<Follow> selectUserFollow(String userId);

    List<Follow> selectUserFans(String userId);

    int selectFollowNumber(String userId);

    int selectFansNumber(String userId);

    int updateByPrimaryKeySelective(Follow record);

    int updateByPrimaryKey(Follow record);
}