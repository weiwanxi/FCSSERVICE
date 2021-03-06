package com.fcsservice.controller;

import com.fcsservice.model.pojo.UserAccount;
import com.fcsservice.service.*;
import com.fcsservice.utils.*;
import org.apache.log4j.Logger;
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
    @Autowired
    DictdataService dictdataService;
    @Autowired
    UserDataService userDataService;
    @Autowired
    CodeService codeService;

    private static Logger logger = Logger.getLogger(AccountController.class);

    /**
     * 登录功能
     * @param user_account 登录账号
     * @param user_password 登录密码
     * @return user_id登录成功|null登录失败
     */
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


    /**
     * 查询用户名是否已注册
     * @param user_account 查询账号
     * @return true账号未注册|false账号已注册
     */
    @RequestMapping(value="/registerConfirm",method = {RequestMethod.POST})
    @ResponseBody
    public Result registerConfirm(@RequestParam("account") String user_account){
        Result result = new Result();

        if (accountService.existUserAccount(user_account)){
            result.setCode(Result.SUCCESS);
            result.setMsg("账号未注册");
        }else{
            result.setCode(Result.FAIL);
            result.setMsg("账号已注册");
        }
        return result;
    }


    /**
     * 注册普通用户
     * @param user_account 注册账号
     * @param user_password 注册密码
     * @param mail 注册邮箱
     * @param user_type 注册类别
     * @return true注册用户成功|false注册用户失败
     */
    @RequestMapping(value="/registerUser",method = {RequestMethod.POST})
    @ResponseBody
    public Result registerUser(@RequestParam("account") String user_account,
                        @RequestParam("password") String user_password,
                        @RequestParam("mail") String mail,
                        @RequestParam("type") int user_type){
        Result result = new Result();

        boolean register = accountService.addUserAccount(user_account,user_password,mail,user_type,"null");

        if (register){
            result.setCode(Result.SUCCESS);
            result.setMsg("注册用户成功");
        }else {
            result.setCode(Result.FAIL);
            result.setMsg("注册用户失败");
        }

        return result;
    }


    /**
     * 注册设计师
     * @param user_account 注册账号
     * @param user_password 注册密码
     * @param mail 注册邮箱
     * @param user_type 注册类别
     * @param tag 设计师标签
     * @return true注册设计师成功|false注册失败
     */
    @RequestMapping(value="/registerDesigner",method = {RequestMethod.POST})
    @ResponseBody
    public Result registerDesigner(@RequestParam("account") String user_account,
                        @RequestParam("password") String user_password,
                        @RequestParam("mail") String mail,
                        @RequestParam("type") int user_type,
                        @RequestParam("tag") String tag){
        Result result = new Result();

        boolean register = accountService.addUserAccount(user_account,user_password,mail,user_type,tag);

        if (register){
            result.setCode(Result.SUCCESS);
            result.setMsg("注册设计师成功");
        }else{
            result.setCode(Result.FAIL);
            result.setMsg("注册失败，信息不完整");
        }

        return result;
    }


    /**
     * 修改密码，通过邮箱验证后
     * @param userId 用户ID
     * @param password 用户新密码
     * @return true修改密码成功|false密码修改失败
     */
    @RequestMapping(value="/updatePassword",method = {RequestMethod.POST})
    @ResponseBody
    public Result updatePassword(@RequestParam("userId") String userId,
                           @RequestParam("password") String password) {
        Result result = new Result();

        if (accountService.getAccountById(userId) != null){
            accountService.updatePassword(userId,password);
            result.setCode(Result.SUCCESS);
            result.setMsg("修改密码成功");
        }else {
            result.setCode(Result.FAIL);
            result.setMsg("密码修改失败");
        }

        return result;
    }


    /**
     * 修改密码，通过旧密码验证
     * @param userId 用户ID
     * @param oldPassword 用户新密码
     * @param newPassword 用户新密码
     * @return true修改密码成功|false密码修改失败
     */
    @RequestMapping(value="/updatePasswordByOldPassword",method = {RequestMethod.POST})
    @ResponseBody
    public Result updatePasswordByOldPassword(@RequestParam("userId") String userId,
                                 @RequestParam("oldPassword") String oldPassword,
                                 @RequestParam("newPassword") String newPassword) {
        Result result = new Result();

        UserAccount userAccount = accountService.getAccountById(userId);

        if (userAccount != null){
            if (oldPassword != null && !"".equals(oldPassword) &&
                    newPassword != null && !"".equals(newPassword)){
                if (oldPassword.equals(userAccount.getUserPassword())){
                    accountService.updatePassword(userId,newPassword);
                    result.setCode(Result.SUCCESS);
                    result.setMsg("修改密码成功");
                }else{
                    result.setCode(Result.FAIL);
                    result.setMsg("旧密码不正确");
                }
            }else {
                result.setCode(Result.FAIL);
                result.setMsg("数据错误");
            }
        }else {
            result.setCode(Result.FAIL);
            result.setMsg("该用户不存在");
        }

        return result;
    }
}
