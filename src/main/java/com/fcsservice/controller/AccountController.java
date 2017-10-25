package com.fcsservice.controller;

import com.fcsservice.model.pojo.UserAccount;
import com.fcsservice.service.AccountService;
import com.fcsservice.utils.Converter;
import com.fcsservice.utils.Result;
import com.mysql.jdbc.log.Log;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.UUID;

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

    private static Logger logger = Logger.getLogger(AccountController.class);

    //登录
    @RequestMapping(value="/login",method = {RequestMethod.POST})
    @ResponseBody
    public Result login(@RequestParam("account") String user_account,
                        @RequestParam("password") String user_password){
        Result result = new Result();

        logger.info("用户： "+user_account+" 请求登录");

        String user_id = accountService.login(user_account,user_password);

        if (user_id != null){
            result.setCode(Result.SUCCESS);
            result.setMsg("登录成功");
            result.setObj(user_id);
            logger.info("用户： "+user_account+" 登录成功");
        }else{
            result.setCode(Result.FAIL);
            result.setMsg("登录失败，账号与密码不匹配");
            result.setObj(null);
            logger.info("用户： "+user_account+"登录失败，账号与密码不匹配");
        }

        return result;
    }

    //查询用户名是否已注册
    @RequestMapping(value="/existUserAccount",method = {RequestMethod.POST})
    @ResponseBody
    public Result existUserAccount(@RequestParam("account") String user_account){
        Result result = new Result();

        boolean exist = accountService.existUserAccount(user_account);

        if (exist){
            result.setCode(Result.FAIL);
            result.setMsg("账号已注册");
        }else{
            result.setCode(Result.SUCCESS);
            result.setMsg("账号未注册");
        }

        return result;
    }

    //注册普通用户
    @RequestMapping(value="/registerUser",method = {RequestMethod.POST})
    @ResponseBody
    public Result registerUser(@RequestParam("account") String user_account,
                        @RequestParam("password") String user_password,
                        @RequestParam("type") int user_type){
        Result result = new Result();

        UserAccount userAccount = new UserAccount();
        String user_id = UUID.randomUUID().toString().replaceAll("-", "");
        if (user_id!=null && user_account!=null && user_password!=null && user_type==1){
            userAccount.setUserId(user_id);
            userAccount.setUserAccount(user_account);
            userAccount.setUserPassword(user_password);
            userAccount.setUserType(user_type);
            userAccount.setUserRegtime(new Converter().getNowTime());
            userAccount.setUserStatus(0);
        }

        if (user_account != null){
            accountService.addUserAccount(userAccount);
            result.setCode(Result.SUCCESS);
            result.setMsg("注册用户成功");
        }else {
            result.setCode(Result.FAIL);
            result.setMsg("注册用户失败");
        }

        return result;
    }

    //注册设计师
    @RequestMapping(value="/registerDesigner",method = {RequestMethod.POST})
    @ResponseBody
    public Result login(@RequestParam("account") String user_account,
                        @RequestParam("password") String user_password,
                        @RequestParam("type") int user_type,
                        @RequestParam("tag") String tag){
        Result result = new Result();

        UserAccount userAccount = new UserAccount();
        String user_id = UUID.randomUUID().toString().replaceAll("-", "");
        if (user_id!=null && user_account!=null && user_password!=null && user_type==2 && tag != null){
            //设计师资料
            userAccount.setUserId(user_id);
            userAccount.setUserAccount(user_account);
            userAccount.setUserPassword(user_password);
            userAccount.setUserType(user_type);
            userAccount.setUserRegtime(new Converter().getNowTime());
            userAccount.setUserStatus(0);

            //设计标签
            //去除字符串最开始的；
            tag = tag.substring(1,tag.length());
            int tagArray[] = new Converter().getIntegerArrayByString(tag,";");

            int tagId[] = accountService.getTagidList(tagArray);

        }

        return result;
    }
}
