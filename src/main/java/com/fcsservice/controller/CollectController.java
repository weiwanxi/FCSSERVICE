package com.fcsservice.controller;

import com.fcsservice.service.CollectService;
import com.fcsservice.utils.FcsserviceUtil;
import com.fcsservice.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by YE on 2017/11/18 0:00.
 */

@Controller
@CrossOrigin
@RequestMapping(value="/CollectController")
public class CollectController {
    @Autowired
    CollectService collectService;

    @RequestMapping(value = "/collect",method = RequestMethod.POST)
    @ResponseBody
    public Result collect(@RequestParam("informationId") String informationId,
                           @RequestParam("userId") String userId,
                           @RequestParam("collectType") int collectType,
                          @RequestParam("FCType") int FCType){
        Result result = new Result();
        String msg;

        if (FCType == FcsserviceUtil.FCfalse){
            msg = collectService.addCollect(informationId,userId,collectType);
            if (msg == null){
                result.setCode(Result.SUCCESS);
                result.setMsg("收藏成功");
            }else {
                result.setCode(Result.FAIL);
                result.setMsg(msg);
            }
        }else {
            msg = collectService.deleteCollect(informationId,userId);
            if (msg == null){
                result.setCode(Result.SUCCESS);
                result.setMsg("取消收藏成功");
            }else {
                result.setCode(Result.FAIL);
                result.setMsg(msg);
            }
        }
        return result;
    }
}
