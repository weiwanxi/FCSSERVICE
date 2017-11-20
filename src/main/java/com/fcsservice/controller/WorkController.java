package com.fcsservice.controller;

import com.fcsservice.service.WorkService;
import com.fcsservice.utils.FcsserviceUtil;
import com.fcsservice.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by YE on 2017/11/19 15:33.
 */

@Controller
@CrossOrigin
@RequestMapping(value="/WorkController")
public class WorkController {
    @Autowired
    WorkService workService;

    @RequestMapping(value = "/getWorkByComment",method = RequestMethod.POST)
    @ResponseBody
    public Result getClothByComment(@RequestParam("page") int page){
        Result result = new Result();

        Map<String,String[]> map = workService.getWorkOrderByComment(page* FcsserviceUtil.PageNumber, FcsserviceUtil.PageNumber);

        if (map != null){
            if (map.get("id").length == 0){
                result.setCode(Result.FAIL);
                result.setMsg("已无更多数据");
            }else {
                result.setCode(Result.SUCCESS);
                result.setObj(map);
                result.setMsg("成功数据");
            }
        }else {
            result.setCode(Result.FAIL);
            result.setMsg("获取数据出错");
        }

        return result;
    }

    @RequestMapping(value = "/getWorkByFabulous",method = RequestMethod.POST)
    @ResponseBody
    public Result getClothByFabulous(@RequestParam("page") int page){
        Result result = new Result();

        Map<String,String[]> map = workService.getWorkOrderByFabulous(page* FcsserviceUtil.PageNumber, FcsserviceUtil.PageNumber);

        if (map != null){
            if (map.get("id").length == 0){
                result.setCode(Result.FAIL);
                result.setMsg("已无更多数据");
            }else {
                result.setCode(Result.SUCCESS);
                result.setObj(map);
                result.setMsg("成功数据");
            }
        }else {
            result.setCode(Result.FAIL);
            result.setMsg("获取数据出错");
        }

        return result;
    }
}
