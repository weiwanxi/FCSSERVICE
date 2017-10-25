package com.fcsservice.controller;

import com.fcsservice.service.AccountService;
import com.fcsservice.utils.Result;
import com.mysql.jdbc.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by YE on 2017/10/15.
 */

@Controller
@CrossOrigin
@SessionAttributes(value="user")
@Scope("prototype")
@RequestMapping(value="/AccountController")
public class AccountController {
    @Autowired
    AccountService accountService;

    @RequestMapping(value="/login",method = {RequestMethod.POST})
    @ResponseBody
    public Result login(@RequestParam("account") String user_account,
                        @RequestParam("password") String user_password){

        System.out.println("用户： "+user_account+" 请求登录");

        boolean validate = accountService.validate(user_account,user_password);
        String msg = validate?"登录成功":"登录失败，密码与账号不匹配";

        System.out.println("用户： "+user_account + msg);


        Result result = new Result();
        result.setCode(Result.SUCCESS);
        result.setMsg(msg);
        result.setObj(validate);

        return result;
    }
}
