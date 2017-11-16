package com.fcsservice.service;

import com.fcsservice.dao.AccountDao;
import com.fcsservice.dao.FollowDao;
import com.fcsservice.model.pojo.Follow;
import com.fcsservice.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

/**
 * Created by YE on 2017/11/15 19:38.
 */

@Service
@Transactional
public class FollowService {
    @Autowired
    FollowDao followDao;
    @Autowired
    AccountDao accountDao;

    public Result setFollow(String userId, String concernId){
        Result result = new Result();

        if (accountDao.getUserAccountById(userId) == null){
            result.setMsg("用户不存在");
            result.setCode(Result.FAIL);
        }else if(accountDao.getUserAccountById(concernId) == null){
            result.setMsg("设计师不存在");
            result.setCode(Result.FAIL);
        }else {
            Follow follow = followDao.getFollowByUserIdAndConcerId(userId,concernId);
            if (follow != null){
                followDao.deleteFollowByUserIdAndConcerId(userId,concernId);
            }else {
                Follow addFollow = new Follow();
                String follow_id = UUID.randomUUID().toString().replaceAll("-", "");
                addFollow.setFollowId(follow_id);
                addFollow.setFollowerId(userId);
                addFollow.setConcernId(concernId);
                addFollow.setFollowTime(new Date());
                followDao.addFollow(addFollow);
            }
            result.setCode(Result.SUCCESS);
        }
        return result;
    }
}
