package com.fcsservice.controller;

import com.fcsservice.service.ClothService;
import com.fcsservice.utils.FcsserviceUtil;
import com.fcsservice.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by YE on 2017/11/19 15:34.
 */

@Controller
@CrossOrigin
@RequestMapping(value="/ClothController")
public class ClothController {
    @Autowired
    ClothService clothService;

    @RequestMapping(value = "/getClothByComment",method = RequestMethod.POST)
    @ResponseBody
    public Result getClothByComment(@RequestParam("page") int page){
        Result result = new Result();

        Map<String,String[]> map = clothService.getClothOrderByComment(page* FcsserviceUtil.PageNumber, FcsserviceUtil.PageNumber);

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

    @RequestMapping(value = "/getClothByFabulous",method = RequestMethod.POST)
    @ResponseBody
    public Result getClothByFabulous(@RequestParam("page") int page){
        Result result = new Result();

        Map<String,String[]> map = clothService.getClothOrderByFabulous(page*FcsserviceUtil.PageNumber, FcsserviceUtil.PageNumber);

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
