package com.fcsservice.controller;

import com.fcsservice.service.AccountService;
import com.fcsservice.utils.Result;
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

        boolean validate = accountService.validate(user_account,user_password);

        String msg = validate?"密码正确":"密码与账号不匹配";

        Result result = new Result();
        result.setCode(Result.SUCCESS);
        result.setMsg(msg);
        result.setObj(validate);

        return result;
    }
}
