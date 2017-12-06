package com.fcsservice.controller;

import com.fcsservice.service.AlbumService;
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
    @Autowired
    AlbumService albumService;


    /**
     * 获取设计作品列表，并通过评论数量排序
     * @param page 页数
     * @param screenType 筛选条件
     * @return 成功获取数据|已无更多数据|获取数据出错
     */
    @RequestMapping(value = "/getWorkByComment",method = RequestMethod.POST)
    @ResponseBody
    public Result getWorkByComment(@RequestParam("page") int page,
                                    @RequestParam("screen") int screenType){
        Result result = new Result();

        Map<String,String[]> map = workService.getWorkOrderByComment(page* FcsserviceUtil.PageNumber, FcsserviceUtil.PageNumber,screenType);

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
     * 获取设计作品列表，并通过点赞数量排序
     * @param page 页数
     * @param screenType 筛选条件
     * @return 成功获取数据|已无更多数据|获取数据出错
     */
    @RequestMapping(value = "/getWorkByFabulous",method = RequestMethod.POST)
    @ResponseBody
    public Result getWorkByFabulous(@RequestParam("page") int page,
                                     @RequestParam("screen") int screenType){
        Result result = new Result();

        Map<String,String[]> map = workService.getWorkOrderByFabulous(page* FcsserviceUtil.PageNumber, FcsserviceUtil.PageNumber,screenType);

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
     * 获取作品详情
     * @param workId 作品ID
     * @param userId 用户ID
     * @return 成功获取数据|获取数据出错
     */
    @RequestMapping(value = "/getWorkById",method = RequestMethod.POST)
    @ResponseBody
    public Result getWorkById(@RequestParam("workId") String workId,
                                 @RequestParam("userId") String userId){

        return workService.getWorkById(userId,workId);
    }


    /**
     * 获取作品信息用于更新作品信息
     * @param workId 作品ID
     * @return 获取作品信息成功|获取作品信息出错
     */
    @RequestMapping(value = "/getWorkToUpdate",method = RequestMethod.POST)
    @ResponseBody
    public Result getWorkToUpdate(@RequestParam("workId") String workId){
        Result result = new Result();

        Map<String,String> map = workService.getWorkToUpdate(workId);
        if (map != null){
            result.setCode(Result.SUCCESS);
            result.setObj(map);
            result.setMsg("获取作品信息成功");
        }else {
            result.setCode(Result.FAIL);
            result.setMsg("获取作品信息出错");
        }
        return result;
    }


    /**
     * 获取专辑内的作品列表
     * @param albumId 专辑ID
     * @return 获取作品列表成功|该专辑不存在
     */
    @RequestMapping(value = "/getWorkList",method = RequestMethod.POST)
    @ResponseBody
    public Result getWorkList(@RequestParam("albumId") String albumId){
        Result result = new Result();

        String albumName = albumService.getAlbum(albumId).get("albumName");
        if (albumName != null){
            result.setCode(Result.SUCCESS);
            result.setObj(workService.getWorkListByAlbumId(albumId));
            result.setObj1(albumName);
            result.setMsg("获取作品列表成功");
        }else {
            result.setCode(Result.FAIL);
            result.setMsg("该专辑不存在");
        }

        return result;
    }

    /**
     * 新增作品
     * @param albumId 专辑ID
     * @param designerId 设计师ID
     * @param workName 作品名
     * @param workColor 作品颜色
     * @param workType 作品类别
     * @param workComponent 作品面料ID
     * @param workStyle 作品风格ID
     * @param workModel 作品款式ID
     * @param workIntro 作品介绍
     * @param workImageArray 作品图片
     * @return 添加作品成功|添加作品失败
     */
    @RequestMapping(value = "/addWork",method = RequestMethod.POST)
    @ResponseBody
    public Result addWork(@RequestParam("albumId") String albumId,
                          @RequestParam("designerId") String designerId,
                          @RequestParam("workName") String workName,
                          @RequestParam("workColor") String workColor,
                          @RequestParam("workType") String workType,
                          @RequestParam("workComponent") String workComponent,
                          @RequestParam("workStyle") String workStyle,
                          @RequestParam("workModel") String workModel,
                          @RequestParam("workIntro") String workIntro,
                          @RequestParam("workImage") String[] workImageArray){
        Result result = new Result();

        boolean resultt = workService.addWork(albumId,designerId,workName,workColor,workType,workComponent,workStyle,workModel,workIntro,workImageArray);

        if (resultt){
            result.setCode(Result.SUCCESS);
            result.setMsg("添加作品成功");
        }else {
            result.setCode(Result.FAIL);
            result.setMsg("添加作品失败");
        }
        return result;
    }


    /**
     * 删除一个作品
     * @param workId 作品ID
     * @return 删除作品成功|该作品不存在
     */
    @RequestMapping(value = "/deleteWork",method = RequestMethod.POST)
    @ResponseBody
    public Result deleteWork(@RequestParam("workId") String workId){
        Result result = new Result();

        boolean resultt = workService.deleteWork(workId);
        if (resultt){
            result.setCode(Result.SUCCESS);
            result.setMsg("删除作品成功");
        }else {
            result.setCode(Result.FAIL);
            result.setMsg("该作品不存在");
        }

        return result;
    }

    /**
     * 更新作品信息
     * @param workId 作品ID
     * @param albumId 专辑ID
     * @param designerId 设计师ID
     * @param workName 作品名
     * @param workColor 作品颜色
     * @param workType 作品类别ID
     * @param workComponent 作品面料ID
     * @param workStyle 作品风格ID
     * @param workModel 作品款式ID
     * @param workIntro 作品介绍
     * @param workImageArray 作品图片
     * @return 更新作品信息成功|更新作品信息失败
     */
    @RequestMapping(value = "/updateWork",method = RequestMethod.POST)
    @ResponseBody
    public Result updateWork(@RequestParam("workId") String workId,
                          @RequestParam("albumId") String albumId,
                          @RequestParam("designerId") String designerId,
                          @RequestParam("workName") String workName,
                          @RequestParam("workColor") String workColor,
                          @RequestParam("workType") String workType,
                          @RequestParam("workComponent") String workComponent,
                          @RequestParam("workStyle") String workStyle,
                          @RequestParam("workModel") String workModel,
                          @RequestParam("workIntro") String workIntro,
                          @RequestParam("workImage") String[] workImageArray){
        Result result = new Result();

        boolean resultt = workService.updateWork(workId,albumId,designerId,workName,workColor,workType,workComponent,workStyle,workModel,workIntro,workImageArray);

        if (resultt){
            result.setCode(Result.SUCCESS);
            result.setMsg("更新作品信息成功");
        }else {
            result.setCode(Result.FAIL);
            result.setMsg("更新作品信息失败");
        }
        return result;
    }


    /**
     * 查询作品
     * @param searchText 查询文字
     * @param page 查询页数
     * @return 获取搜索结果成功|获取搜索结果失败
     */
    @RequestMapping(value = "/searchWork",method = RequestMethod.POST)
    @ResponseBody
    public Result searchWork(@RequestParam("searchText") String searchText,
                              @RequestParam("page") int page){
        Result result = new Result();

        Map<String,String[]> map = workService.getWorkBySearch(searchText,page* FcsserviceUtil.PageNumber, FcsserviceUtil.PageNumber);

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
