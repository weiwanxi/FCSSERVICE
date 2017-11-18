package com.fcsservice.controller;

import com.fcsservice.service.DesignerService;
import com.fcsservice.service.DictdataService;
import com.fcsservice.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by YE on 2017/11/15 9:40.
 */

@Controller
@CrossOrigin
@RequestMapping(value="/DesignerController")
public class DesignerController {
    @Autowired
    DesignerService designerService;

    @RequestMapping(value = "/getDesignerList",method = RequestMethod.POST)
    @ResponseBody
    public Result getDesignerList(@RequestParam("userId") String userId,
                                  @RequestParam("lastDesignerId") String lastDesignerId){
        Result result = new Result();

        Map<String,String[]> map = designerService.getDesignerList(userId,lastDesignerId);

        if (map != null){
            result.setCode(Result.SUCCESS);
            result.setObj(map);
        }else {
            if (lastDesignerId != null && !"null".equals(lastDesignerId)) {
                result.setMsg("已无更多设计师信息");
            }else{
                result.setMsg("暂无设计师信息");
            }
            result.setCode(Result.FAIL);
        }
        return result;
    }

    @RequestMapping(value = "/getDesignerListByTag",method = RequestMethod.POST)
    @ResponseBody
    public Result getDesignerListByTag(@RequestParam("userId") String userId,
                                  @RequestParam("tag") String tag){
        Result result = new Result();

        //设计标签
        tag = tag.substring(1,tag.length());
        String[] tagStrArray = tag.split(";");

        Map<String,String[]> map = designerService.getDesignerListByTag(userId,tagStrArray);

        if (map != null){
            result.setCode(Result.SUCCESS);
            result.setObj(map);
        }else {
            result.setMsg("暂无此类设计师信息");
            result.setCode(Result.FAIL);
        }
        return result;
    }

    @RequestMapping(value = "/getDesigner",method = RequestMethod.POST)
    @ResponseBody
    public Result getDesigner(@RequestParam("designerId") String designerId,
                              @RequestParam("userId") String userId){
        return designerService.getDeisgner(designerId,userId);
    }
}
