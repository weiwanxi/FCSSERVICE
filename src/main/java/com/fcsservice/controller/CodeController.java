package com.fcsservice.controller;

import com.fcsservice.model.pojo.Code;
import com.fcsservice.service.CodeService;
import com.fcsservice.service.UserDataService;
import com.fcsservice.utils.EmailUtil;
import com.fcsservice.utils.RandomCode;
import com.fcsservice.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by YE on 2017/10/27 19:26.
 */

@Controller
@CrossOrigin
@RequestMapping(value="/CodeController")
public class CodeController {
    @Autowired
    CodeService codeService;
    @Autowired
    UserDataService userDataService;

    @RequestMapping(value="/confirmMailCode",method = {RequestMethod.POST})
    @ResponseBody
    public Result confirmMailCode(@RequestParam("mail") String mail,
                        @RequestParam("code") String code,
                        @RequestParam("type") int type){
        Result result = new Result();

        Code codee = codeService.sentMail(mail,type);
        if (codee != null){
            if (codee.getCode().equals(code)){
                String userId = userDataService.getUserId(mail);
                result.setObj(userId);
                result.setCode(Result.SUCCESS);
                result.setMsg("验证成功");
            }else{
                result.setCode(Result.FAIL);
                result.setMsg("验证码错误");
            }
        }else{
            result.setCode(Result.FAIL);
            result.setMsg("邮箱验证码已过期");
        }
        return result;
    }

    //查询邮箱是否已绑定账号，并发送邮件
    @RequestMapping(value="/sendMail",method = {RequestMethod.POST})
    @ResponseBody
    public Result sendMail(@RequestParam("mail") String mail,
                            @RequestParam("type") int type){
        Result result = new Result();
        String subject;

        if (type == 1)
            subject = "轻纺商圈-绑定邮箱";
        else if(type == 2)
            subject = "轻纺商圈-重置密码";
        else
            subject = "轻纺商圈-更换邮箱";

        //验证邮箱是否已绑定账号
        if ((userDataService.existMail(mail) && type != 1) || (!userDataService.existMail(mail) && type == 1)){
            //确认未发送过验证码
            if (codeService.sentMail(mail,type) == null){
                //新建线程发送验证码
                String code = RandomCode.getCode();
                EmailUtil emailUtil = new EmailUtil(mail,subject,code);
                new Thread(emailUtil).start();

                //通过验证码管理器保存，删除验证码
                codeService.saveCode(mail,code,type);

                result.setCode(Result.SUCCESS);
                result.setMsg("已发送验证码");
            }else{
                result.setCode(Result.SUCCESS);
                result.setMsg("验证码未过期");
            }
        }else if(userDataService.existMail(mail) && type == 1){
            result.setCode(Result.FAIL);
            result.setMsg("邮箱已绑定账号");
        }else{
            result.setCode(Result.FAIL);
            result.setMsg("邮箱未绑定账号");
        }
        return result;
    }
}
