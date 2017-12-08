package com.fcsservice.dao;

import com.fcsservice.model.dto.FollowMapper;
import com.fcsservice.model.pojo.Follow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by YE on 2017/11/15 16:15.
 */

@Repository
public class FollowDao {
    @Autowired
    FollowMapper followMapper;

    public String getFollowType(String userId,String designerId){
        Follow follow = followMapper.selectFollowed(userId,designerId);
        if (follow != null)
            return "followed";
        else
            return "unfollow";
    }

    public Follow getFollowByUserIdAndConcerId(String userId,String concernId){
        return followMapper.selectFollowed(userId,concernId);
    }

    public void deleteFollowByUserIdAndConcerId(String userId,String concernId){
        followMapper.deleteByUCId(userId,concernId);
    }

    public void addFollow(Follow follow){
        followMapper.insert(follow);
    }

    public int getFollowNumber(String userId){
        return followMapper.selectFollowNumber(userId);
    }

    public int getFansNumber(String userId){
        return followMapper.selectFansNumber(userId);
    }

    public List<Follow> getUserFollow(String userId){
        return followMapper.selectUserFollow(userId);
    }

    public List<Follow> getUserFans(String userId){
        return followMapper.selectUserFans(userId);
    }
}
