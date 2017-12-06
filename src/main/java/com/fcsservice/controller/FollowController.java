package com.fcsservice.controller;

import com.fcsservice.service.FollowService;
import com.fcsservice.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by YE on 2017/11/15 19:28.
 */

@Controller
@CrossOrigin
@RequestMapping(value="/FollowController")
public class FollowController {
    @Autowired
    FollowService followService;

    /**
     * 关注设计师
     * @param userId 用户ID
     * @param concernId 被关注这ID
     * @return
     */
    @RequestMapping(value = "/setFollow",method = RequestMethod.POST)
    @ResponseBody
    public Result setFollow(@RequestParam("userId") String userId,
                            @RequestParam("concernId") String concernId){
        return followService.setFollow(userId,concernId);
    }


    /**
     * 获取用户关注列表
     * @param userId 用户ID
     * @return 获取关注列表成功|暂无关注
     */
    @RequestMapping(value = "/getUserFollow",method = RequestMethod.POST)
    @ResponseBody
    public Result getUserFollow(@RequestParam("userId") String userId){
        Result result = new Result();
        Map<String,String[]> map = followService.getUserFollow(userId);
        if (map != null){
            result.setCode(Result.SUCCESS);
            result.setObj(map);
            result.setMsg("获取关注列表成功");
        }else {
            result.setCode(Result.FAIL);
            result.setMsg("暂无关注");
        }
        return result;
    }


    /**
     * 获取设计师粉丝列表
     * @param userId 用户Id
     * @return 获取粉丝列表成功|暂无粉丝
     */
    @RequestMapping(value = "/getUserFans",method = RequestMethod.POST)
    @ResponseBody
    public Result getUserFans(@RequestParam("userId") String userId){
        Result result = new Result();
        Map<String,String[]> map = followService.getUserFans(userId);
        if (map != null){
            result.setCode(Result.SUCCESS);
            result.setObj(map);
            result.setMsg("获取粉丝列表成功");
        }else {
            result.setCode(Result.FAIL);
            result.setMsg("暂无粉丝");
        }
        return result;
    }
}
