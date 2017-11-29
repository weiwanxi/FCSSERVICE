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

    @RequestMapping(value = "/getWorkById",method = RequestMethod.POST)
    @ResponseBody
    public Result getWorkById(@RequestParam("workId") String workId,
                                 @RequestParam("userId") String userId){

        return workService.getWorkById(userId,workId);
    }

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

}
