package com.fcsservice.controller;

import com.fcsservice.service.CommentService;
import com.fcsservice.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by YE on 2017/11/30 16:13.
 */

@Controller
@CrossOrigin
@RequestMapping(value="/CommentController")
public class CommentController {
    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/getCommentList", method = RequestMethod.POST)
    @ResponseBody
    public Result getCommentList(@RequestParam("informationId") String informationId,
                                 @RequestParam("userId") String userId){
        Result result = new Result();

        Map<String,String[]> map = commentService.getCommentList(informationId,userId);

        if (map != null){
            result.setCode(Result.SUCCESS);
            result.setObj(map);
            result.setMsg("获取评论列表成功");
        }else {
            result.setCode(Result.FAIL);
            result.setMsg("暂无无评论");
        }

        return result;
    }

    @RequestMapping(value = "/publishComment", method = RequestMethod.POST)
    @ResponseBody
    public Result publishComment(@RequestParam("userId") String userId,
                                 @RequestParam("comment") String comment,
                                 @RequestParam("informationId") String informationId,
                                 @RequestParam("informationType") String informationType){
        Result result = new Result();

        commentService.saveComment(userId,comment,informationId,informationType);

        result.setCode(Result.SUCCESS);
        result.setMsg("评论成功");

        return result;
    }

    @RequestMapping(value = "/getCommentNumber", method = RequestMethod.POST)
    @ResponseBody
    public Result getCommentNumber(@RequestParam("informationId") String informationId){
        Result result = new Result();

        int number = commentService.getCommentNumber(informationId);

        result.setCode(Result.SUCCESS);
        result.setObj(number);
        result.setMsg("获取评论数量成功");

        return result;
    }

    @RequestMapping(value = "/getUserCommentList", method = RequestMethod.POST)
    @ResponseBody
    public Result getUserCommentList(@RequestParam("userId") String userId){
        Result result = new Result();

        Map<String,String[]> map = commentService.getUserCommentList(userId);

        if (map != null){
            result.setCode(Result.SUCCESS);
            result.setObj(map);
            result.setMsg("获取评论列表成功");
        }else {
            result.setCode(Result.FAIL);
            result.setMsg("暂无无评论");
        }

        return result;
    }

    @RequestMapping(value = "/deleteComment", method = RequestMethod.POST)
    @ResponseBody
    public Result deleteComment(@RequestParam("commentId") String commentId){
        Result result = new Result();

        boolean resultt = commentService.deleteComment(commentId);

        if (resultt){
            result.setCode(Result.SUCCESS);
            result.setMsg("删除评论成功");
        }else {
            result.setCode(Result.FAIL);
            result.setMsg("该评论不存在");
        }

        return result;
    }

    @RequestMapping(value = "/deleteAllComment", method = RequestMethod.POST)
    @ResponseBody
    public Result deleteAllComment(@RequestParam("userId") String userId){
        Result result = new Result();

        commentService.deleteAllComment(userId);
        result.setCode(Result.SUCCESS);
        result.setMsg("删除评论成功");

        return result;
    }
}
