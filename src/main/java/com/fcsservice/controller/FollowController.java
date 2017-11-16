package com.fcsservice.controller;

import com.fcsservice.service.FollowService;
import com.fcsservice.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by YE on 2017/11/15 19:28.
 */

@Controller
@CrossOrigin
@RequestMapping(value="/FollowController")
public class FollowController {
    @Autowired
    FollowService followService;

    @RequestMapping(value = "/setFollow",method = RequestMethod.POST)
    @ResponseBody
    public Result setFollow(@RequestParam("userId") String userId,
                            @RequestParam("concernId") String concernId){
        return followService.setFollow(userId,concernId);
    }
}
