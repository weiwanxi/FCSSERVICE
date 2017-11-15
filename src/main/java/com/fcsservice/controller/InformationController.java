package com.fcsservice.controller;

import com.fcsservice.service.InformationService;
import com.fcsservice.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by YE on 2017/11/10 16:18.
 */

@Controller
@CrossOrigin
@RequestMapping(value="/InformationController")
public class InformationController {
    @Autowired
    InformationService informationService;

    @RequestMapping(value="/getTopInformation",method = {RequestMethod.POST})
    @ResponseBody
    public Result getTopInformation() {
        Result result = new Result();

        result.setObj(informationService.getTopInformation());
        result.setCode(Result.SUCCESS);

        return result;
    }

    @RequestMapping(value="/getInformationList",method = {RequestMethod.POST})
    @ResponseBody
    public Result getInformationList() {
        Result result = new Result();

        result.setObj(informationService.getInformationList());
        result.setCode(Result.SUCCESS);

        return result;
    }

    @RequestMapping(value="/getNewInformationList",method = {RequestMethod.POST})
    @ResponseBody
    public Result getNewInformationList( @RequestParam("newInformation") String newInformation) {
        Result result = new Result();

        Map<String,String[]> map;
        if (newInformation == null || "".equals(newInformation) || "null".equals(newInformation)){
            map = null;
        }else {
            map = informationService.getNewInformationList(newInformation);
        }
        if (map != null){
            result.setObj(map);
            result.setCode(Result.SUCCESS);
            result.setMsg("成功更新"+map.get("id").length+"条资讯");
        }else {
            result.setCode(Result.FAIL);
            result.setMsg("成功更新0条资讯");
        }
        return result;
    }

    @RequestMapping(value="/getOldInformationList",method = {RequestMethod.POST})
    @ResponseBody
    public Result getOldInformationList( @RequestParam("oldInformation") String oldInformation) {
        Result result = new Result();

        Map<String,String[]> map = informationService.getOldInformationList(oldInformation);
        if (map != null){
            result.setObj(map);
            result.setCode(Result.SUCCESS);
            result.setMsg("成功加载"+map.get("id").length+"条资讯");
        }else {
            result.setCode(Result.FAIL);
            result.setMsg("已无更多资讯");
        }
        return result;
    }
}
