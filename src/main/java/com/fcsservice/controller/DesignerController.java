package com.fcsservice.controller;

import com.fcsservice.service.DesignerService;
import com.fcsservice.service.DictdataService;
import com.fcsservice.utils.FcsserviceUtil;
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

    /**
     * 获取设计师列表
     * @param userId 用户ID
     * @param lastDesignerId 最后设计师ID
     * @return 已无更多设计师信息|暂无设计师信息
     */
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


    /**
     * 通过标签查询设计师
     * @param userId 用户ID
     * @param tag 查询标签
     * @return 设计师列表|暂无此类设计师信息
     */
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

    /**
     * 获取设计师详情
     * @param designerId 设计师ID
     * @param userId 用户ID
     * @return 设计师详情|该设计师不存在
     */
    @RequestMapping(value = "/getDesigner",method = RequestMethod.POST)
    @ResponseBody
    public Result getDesigner(@RequestParam("designerId") String designerId,
                              @RequestParam("userId") String userId){
        return designerService.getDeisgner(designerId,userId);
    }


    /**
     * 查询设计师
     * @param searchText 查询文字
     * @param page 查询页数
     * @return
     */
    @RequestMapping(value = "/searchDesigner",method = RequestMethod.POST)
    @ResponseBody
    public Result searchDesigner(@RequestParam("searchText") String searchText,
                                @RequestParam("page") int page){
        Result result = new Result();

        Map<String,String[]> map = designerService.getDesignerBySearch(searchText,page* FcsserviceUtil.PageNumber, FcsserviceUtil.PageNumber);

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