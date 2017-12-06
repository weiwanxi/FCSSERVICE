package com.fcsservice.service;

import com.fcsservice.dao.AccountDao;
import com.fcsservice.dao.FollowDao;
import com.fcsservice.dao.UserDataDao;
import com.fcsservice.model.pojo.Follow;
import com.fcsservice.model.pojo.UserAccount;
import com.fcsservice.model.pojo.UserData;
import com.fcsservice.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    @Autowired
    UserDataDao userDataDao;

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

    public Map<String,String[]> getUserFollow(String userId){
        Map<String,String[]> map = new HashMap<String, String[]>();
        List<Follow> followList = followDao.getUserFollow(userId);

        if (followList != null){
            String[] portrait = new String[followList.size()];
            String[] designerId = new String[followList.size()];
            String[] designerName = new String[followList.size()];
            for (int i=0;i<followList.size();i++){
                UserAccount account = accountDao.getUserAccountById(followList.get(i).getConcernId());
                UserData data = userDataDao.getUserDataByUserId(followList.get(i).getConcernId());
                designerId[i] = followList.get(i).getConcernId();
                if (account != null){
                    designerName[i] = account.getUserAccount();
                }else {
                    designerName[i] = "已注销";
                    designerId[i] = "";
                }
                if (data != null){
                    portrait[i] = data.getDataPortrait();
                }else {
                    portrait[i] = "";
                }
            }

            map.put("designerId",designerId);
            map.put("portrait",portrait);
            map.put("designerName",designerName);

            return map;
        }else {
            return null;
        }
    }

    public Map<String,String[]> getUserFans(String userId){
        Map<String,String[]> map = new HashMap<String, String[]>();
        List<Follow> followList = followDao.getUserFans(userId);

        if (followList != null){
            String[] portrait = new String[followList.size()];
            String[] designerId = new String[followList.size()];
            String[] designerName = new String[followList.size()];
            for (int i=0;i<followList.size();i++){
                UserAccount account = accountDao.getUserAccountById(followList.get(i).getFollowerId());
                UserData data = userDataDao.getUserDataByUserId(followList.get(i).getFollowerId());
                designerId[i] = followList.get(i).getFollowerId();
                if (account != null){
                    designerName[i] = account.getUserAccount();
                }else {
                    designerName[i] = "已注销";
                    designerId[i] = "";
                }
                if (data != null){
                    portrait[i] = data.getDataPortrait();
                }else {
                    portrait[i] = "";
                }
            }

            map.put("designerId",designerId);
            map.put("portrait",portrait);
            map.put("designerName",designerName);

            return map;
        }else {
            return null;
        }
    }
}
