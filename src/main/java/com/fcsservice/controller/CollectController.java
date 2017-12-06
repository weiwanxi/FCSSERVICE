package com.fcsservice.controller;

import com.fcsservice.service.CollectService;
import com.fcsservice.utils.FcsserviceUtil;
import com.fcsservice.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by YE on 2017/11/18 0:00.
 */

@Controller
@CrossOrigin
@RequestMapping(value="/CollectController")
public class CollectController {
    @Autowired
    CollectService collectService;

    /**
     * 收藏|取消收藏 信息
     * @param informationId 收藏信息ID
     * @param userId 用户ID
     * @param collectType 收藏信息类别
     * @param FCType 收藏状态
     * @return 收藏成功|取消收藏成功
     */
    @RequestMapping(value = "/collect", method = RequestMethod.POST)
    @ResponseBody
    public Result collect(@RequestParam("informationId") String informationId,
                          @RequestParam("userId") String userId,
                          @RequestParam("collectType") int collectType,
                          @RequestParam("FCType") int FCType) {
        Result result = new Result();
        String msg;

        if (FCType == FcsserviceUtil.FCfalse) {
            msg = collectService.addCollect(informationId, userId, collectType);
            if (msg == null) {
                result.setCode(Result.SUCCESS);
                result.setMsg("收藏成功");
            } else {
                result.setCode(Result.FAIL);
                result.setMsg(msg);
            }
        } else {
            msg = collectService.deleteCollect(informationId, userId);
            if (msg == null) {
                result.setCode(Result.SUCCESS);
                result.setMsg("取消收藏成功");
            } else {
                result.setCode(Result.FAIL);
                result.setMsg(msg);
            }
        }
        return result;
    }


    /**
     * 获取收藏列表
     * @param type 收藏信息类别
     * @param sort 排序方式
     * @param userId 用户ID
     * @return 获取收藏列表成功|暂无此类收藏信息
     */
    @RequestMapping(value = "/getCollect", method = RequestMethod.POST)
    @ResponseBody
    public Result getCollect(@RequestParam("type") String type,
                             @RequestParam("sort") String sort,
                             @RequestParam("userId") String userId) {
        Result result = new Result();

        Map<String, String[]> map = collectService.getCollect(type, sort, userId);
        if (map != null) {
            result.setCode(Result.SUCCESS);
            result.setObj(map);
            result.setMsg("获取收藏列表成功");
        } else {
            result.setCode(Result.FAIL);
            result.setMsg("暂无此类收藏信息");
        }
        return result;
    }
}