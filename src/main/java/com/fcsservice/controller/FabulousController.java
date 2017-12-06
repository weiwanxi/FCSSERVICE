package com.fcsservice.controller;

import com.fcsservice.service.FabulousService;
import com.fcsservice.utils.FcsserviceUtil;
import com.fcsservice.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by YE on 2017/11/18 0:09.
 */

@Controller
@CrossOrigin
@RequestMapping(value="/FabulousController")
public class FabulousController {
    @Autowired
    FabulousService fabulousService;

    /**
     * 点赞|取消点赞 信息
     * @param informationId 信息ID
     * @param userId 用户ID
     * @param FCType 点赞情况
     * @return 点赞成功|取消点赞成功
     */
    @RequestMapping(value = "/fabulous",method = RequestMethod.POST)
    @ResponseBody
    public Result fabulous(@RequestParam("informationId") String informationId,
                             @RequestParam("userId") String userId,
                              @RequestParam("FCType") int FCType) {
        Result result = new Result();
        String msg;

        if (FCType == FcsserviceUtil.FCfalse){
            msg = fabulousService.addFabulous(informationId,userId);
            if (msg == null){
                result.setCode(Result.SUCCESS);
                result.setMsg("点赞成功");
            }else {
                result.setCode(Result.FAIL);
                result.setMsg(msg);
            }
        }else {
            msg = fabulousService.deleteFabulous(informationId,userId);
            if (msg == null){
                result.setCode(Result.SUCCESS);
                result.setMsg("取消点赞成功");
            }else {
                result.setCode(Result.FAIL);
                result.setMsg(msg);
            }
        }
        return result;
    }

}
