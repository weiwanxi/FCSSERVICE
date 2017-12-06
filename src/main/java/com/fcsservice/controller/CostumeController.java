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

    /**
     * 获取服装列表，并按照评论数排序
     * @param page 页数
     * @param screenType 筛选类别
     * @return 已无更多数据|成功获取数据|获取数据出错
     */
    @RequestMapping(value = "/getCostumeByComment",method = RequestMethod.POST)
    @ResponseBody
    public Result getCostumeByComment(@RequestParam("page") int page,
                                      @RequestParam("screen") int screenType){
        Result result = new Result();

        Map<String,String[]> map = costumeService.getCostumeOrderByComment(page*FcsserviceUtil.PageNumber, FcsserviceUtil.PageNumber,screenType);

        if (map != null){
            if (map.get("id").length == 0){
                result.setCode(Result.FAIL);
                result.setMsg("已无更多数据");
            }else {
                result.setCode(Result.SUCCESS);
                result.setObj(map);
                result.setMsg("成功获取数据");
            }
        }else {
            result.setCode(Result.FAIL);
            result.setMsg("获取数据出错");
        }

        return result;
    }


    /**
     * 获取服装列表，并按照点赞数排序
     * @param page 页数
     * @param screenType 筛选类别
     * @return 已无更多数据|成功获取数据|获取数据出错
     */
    @RequestMapping(value = "/getCostumeByFabulous",method = RequestMethod.POST)
    @ResponseBody
    public Result getCostumeByFabulous(@RequestParam("page") int page,
                                       @RequestParam("screen") int screenType){
        Result result = new Result();

        Map<String,String[]> map = costumeService.getCostumeOrderByFabulous(page*FcsserviceUtil.PageNumber, FcsserviceUtil.PageNumber,screenType);

        if (map != null){
            if (map.get("id").length == 0){
                result.setCode(Result.FAIL);
                result.setMsg("已无更多数据");
            }else {
                result.setCode(Result.SUCCESS);
                result.setObj(map);
                result.setMsg("成功获取数据");
            }
        }else {
            result.setCode(Result.FAIL);
            result.setMsg("获取数据出错");
        }

        return result;
    }

    /**
     * 获取服装详情
     * @param costumeId 服装ID
     * @param userId 用户ID
     * @return 成功数据|获取数据出错
     */
    @RequestMapping(value = "/getCostumeById",method = RequestMethod.POST)
    @ResponseBody
    public Result getCostumeById(@RequestParam("costumeId") String costumeId,
                                 @RequestParam("userId") String userId){

        return costumeService.getCostumeById(userId,costumeId);
    }


    /**
     * 查询服装
     * @param searchText 查询文字
     * @param page 页数
     * @return 获取搜索结果成功|获取搜索结果失败
     */
    @RequestMapping(value = "/searchCostume",method = RequestMethod.POST)
    @ResponseBody
    public Result searchCostume(@RequestParam("searchText") String searchText,
                              @RequestParam("page") int page){
        Result result = new Result();

        Map<String,String[]> map = costumeService.getCostumeBySearch(searchText,page* FcsserviceUtil.PageNumber, FcsserviceUtil.PageNumber);

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
