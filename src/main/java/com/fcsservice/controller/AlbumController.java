package com.fcsservice.controller;

import com.fcsservice.service.AlbumService;
import com.fcsservice.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by YE on 2017/11/25 15:48.
 */

@Controller
@CrossOrigin
@RequestMapping(value="/AlbumController")
public class AlbumController {
    @Autowired
    AlbumService albumService;

    /**
     * 获取用户专辑列表
     * @param userId 用户ID
     * @return true获取专辑列表成功|false获取专辑列表失败
     */
    @RequestMapping(value = "/getAlbumList",method = RequestMethod.POST)
    @ResponseBody
    public Result getAlbumList(@RequestParam("userId") String userId){
        Result result = new Result();

        Map<String,String[]> map = albumService.getAlbumListByUserId(userId);
        if (map != null){
            result.setObj(map);
            result.setCode(Result.SUCCESS);
            result.setMsg("获取专辑列表成功");
        }else {
            result.setCode(Result.FAIL);
            result.setMsg("获取专辑列表失败");
        }

        return result;
    }


    /**
     * 获取专辑详情
     * @param albumId 专辑ID
     * @return true获取专辑成功|false获取专辑失败
     */
    @RequestMapping(value = "/getAlbum",method = RequestMethod.POST)
    @ResponseBody
    public Result getAlbum(@RequestParam("albumId") String albumId){
        Result result = new Result();

        Map<String,String> map = albumService.getAlbum(albumId);

        if (map != null){
            result.setObj(map);
            result.setCode(Result.SUCCESS);
            result.setMsg("获取专辑成功");
        }else {
            result.setCode(Result.FAIL);
            result.setMsg("获取专辑失败");
        }

        return result;
    }


    /**
     * 新增专辑与修改专辑
     * @param userId 用户ID
     * @param albumId 专辑ID
     * @param base64Data 专辑封面
     * @param albumName 专辑名称
     * @return
     */
    @RequestMapping(value = "/addAlbum",method = RequestMethod.POST)
    @ResponseBody
    public Result getAlbum(@RequestParam("userId") String userId,
                           @RequestParam("albumId") String albumId,
                           @RequestParam("image") String base64Data,
                           @RequestParam("albumName") String albumName){
        Result result = new Result();


        if (!"null".equals(albumId) && albumId != null){
            boolean resultt = albumService.updateAlbum(userId,albumId,base64Data,albumName);
            if (resultt){
                result.setCode(Result.SUCCESS);
                result.setMsg("修改专辑成功");
            }else {
                result.setCode(Result.FAIL);
                result.setMsg("修改专辑失败");
            }
        }else{
            if (!albumService.getAlbumByAlbumName(userId,albumName)) {
                boolean resultt = albumService.addAlbum(userId, base64Data, albumName);
                if (resultt){
                    result.setCode(Result.SUCCESS);
                    result.setMsg("添加专辑成功");
                }else {
                    result.setCode(Result.FAIL);
                    result.setMsg("添加专辑失败");
                }
            }else {
                result.setCode(Result.FAIL);
                result.setMsg("添加专辑失败，专辑名称已存在");
            }
        }

        return result;
    }

    /**
     * 删除一个专辑
     * @param albumId 专辑ID
     * @return true删除专辑成功|false删除失败
     */
    @RequestMapping(value = "/deleteAlbum",method = RequestMethod.POST)
    @ResponseBody
    public Result deleteAlbum(@RequestParam("albumId") String albumId){
        Result result = new Result();

        if (albumService.deleteAlbum(albumId)) {
            result.setCode(Result.SUCCESS);
            result.setMsg("删除专辑成功");
        }else {
            result.setCode(Result.FAIL);
            result.setMsg("删除失败，该专辑不存在");
        }

        return result;
    }
}
