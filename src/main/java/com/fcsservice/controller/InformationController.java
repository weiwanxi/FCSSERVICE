package com.fcsservice.controller;

import com.fcsservice.service.InformationService;
import com.fcsservice.utils.FcsserviceUtil;
import com.fcsservice.utils.Result;
import com.sun.org.apache.regexp.internal.RE;
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


    /**
     * 获取置顶的资讯信息
     * @return 资讯列表
     */
    @RequestMapping(value="/getTopInformation",method = {RequestMethod.POST})
    @ResponseBody
    public Result getTopInformation() {
        Result result = new Result();

        result.setObj(informationService.getTopInformation());
        result.setCode(Result.SUCCESS);

        return result;
    }


    /**
     * 获取资讯列表
     * @return 资讯列表
     */
    @RequestMapping(value="/getInformationList",method = {RequestMethod.POST})
    @ResponseBody
    public Result getInformationList() {
        Result result = new Result();

        result.setObj(informationService.getInformationList());
        result.setCode(Result.SUCCESS);

        return result;
    }


    /**
     * 获取最新资讯列表
     * @param newInformation 资讯ID
     * @return 资讯列表
     */
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
            if(map.get("id").length != 0){
                result.setMsg("成功更新"+map.get("id").length+"条资讯");
            }
        }else {
            result.setCode(Result.FAIL);
            result.setMsg("成功更新0条资讯");
        }
        return result;
    }


    /**
     * 获取旧的资讯列表
     * @param oldInformation 资讯ID
     * @return 资讯列表
     */
    @RequestMapping(value="/getOldInformationList",method = {RequestMethod.POST})
    @ResponseBody
    public Result getOldInformationList( @RequestParam("oldInformation") String oldInformation) {
        Result result = new Result();

        Map<String,String[]> map = informationService.getOldInformationList(oldInformation);
        if (map != null){
            result.setObj(map);
            result.setCode(Result.SUCCESS);
            if (map.get("id").length != 0){
                result.setMsg("成功加载"+map.get("id").length+"条资讯");
            }
        }else {
            result.setCode(Result.FAIL);
            result.setMsg("已无更多资讯");
        }
        return result;
    }


    /**
     * 获取资讯详情
     * @param informationId 资讯ID
     * @param userId 用户ID
     * @return 资讯详情|该资讯不存在
     */
    @RequestMapping(value = "/getInformation",method = RequestMethod.POST)
    @ResponseBody
    public Result getInformation(@RequestParam("informationId") String informationId,
                                 @RequestParam("userId") String userId){
        return informationService.getInformationById(informationId,userId);
    }


    /**
     * 查询资讯
     * @param searchText 查询文字
     * @param page 查询页数
     * @return 获取搜索结果成功|获取搜索结果失败
     */
    @RequestMapping(value = "/searchInformation",method = RequestMethod.POST)
    @ResponseBody
    public Result searchInformation(@RequestParam("searchText") String searchText,
                                    @RequestParam("page") int page){
        Result result = new Result();

        Map<String,String[]> map = informationService.getInformationBySearch(searchText,page* FcsserviceUtil.PageNumber, FcsserviceUtil.PageNumber);

        if (map != null){
            result.setCode(Result.SUCCESS);
            result.setObj(map);
            result.setMsg("获取搜索结果成功");
        }else {
            result.setCode(Result.FAIL);
            result.setMsg("获取搜索结果失败");
        }

        return result;
    }
}
