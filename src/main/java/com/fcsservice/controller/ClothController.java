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

    /**
     * 获取布料列表通过评论数排序
     * @param page 页数
     * @param screenType 筛选类别
     * @return true布料列表map|false获取数据出错
     */
    @RequestMapping(value = "/getClothByComment",method = RequestMethod.POST)
    @ResponseBody
    public Result getClothByComment(@RequestParam("page") int page,
                                    @RequestParam("screen") int screenType){
        Result result = new Result();

        Map<String,String[]> map = clothService.getClothOrderByComment(page* FcsserviceUtil.PageNumber, FcsserviceUtil.PageNumber,screenType);

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


    /**
     * 获取布料列表通过点赞数排序
     * @param page 页数
     * @param screenType 筛选类别
     * @return true布料列表map|false获取数据出错
     */
    @RequestMapping(value = "/getClothByFabulous",method = RequestMethod.POST)
    @ResponseBody
    public Result getClothByFabulous(@RequestParam("page") int page,
                                     @RequestParam("screen") int screenType){
        Result result = new Result();

        Map<String,String[]> map = clothService.getClothOrderByFabulous(page*FcsserviceUtil.PageNumber, FcsserviceUtil.PageNumber,screenType);

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


    /**
     * 获取布料详情
     * @param clothId 布料ID
     * @param userId 用户ID
     * @return true布料详情（包括相关推荐）|false获取数据出错
     */
    @RequestMapping(value = "/getClothById",method = RequestMethod.POST)
    @ResponseBody
    public Result getCostumeById(@RequestParam("clothId") String clothId,
                                 @RequestParam("userId") String userId){

        return clothService.getClothById(userId,clothId);
    }


    /**
     * 查询布料
     * @param searchText 查询文字
     * @param page 页数
     * @return true获取搜索结果成功|false获取搜索结果成功
     */
    @RequestMapping(value = "/searchCloth",method = RequestMethod.POST)
    @ResponseBody
    public Result searchCloth(@RequestParam("searchText") String searchText,
                                    @RequestParam("page") int page){
        Result result = new Result();

        Map<String,String[]> map = clothService.getClothBySearch(searchText,page* FcsserviceUtil.PageNumber, FcsserviceUtil.PageNumber);

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
