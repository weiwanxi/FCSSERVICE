package com.fcsservice.controller;

import com.fcsservice.service.CostumeService;
import com.fcsservice.utils.FcsserviceUtil;
import com.fcsservice.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by YE on 2017/11/19 15:32.
 */

@Controller
@CrossOrigin
@RequestMapping(value="/CostumeController")
public class CostumeController {
    @Autowired
    CostumeService costumeService;

    @RequestMapping(value = "/getCostumeByComment",method = RequestMethod.POST)
    @ResponseBody
    public Result getCostumeByComment(@RequestParam("page") int page){
        Result result = new Result();

        Map<String,String[]> map = costumeService.getCostumeOrderByComment(page*FcsserviceUtil.PageNumber, FcsserviceUtil.PageNumber);

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

    @RequestMapping(value = "/getCostumeByFabulous",method = RequestMethod.POST)
    @ResponseBody
    public Result getCostumeByFabulous(@RequestParam("page") int page){
        Result result = new Result();

        Map<String,String[]> map = costumeService.getCostumeOrderByFabulous(page*FcsserviceUtil.PageNumber, FcsserviceUtil.PageNumber);

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
