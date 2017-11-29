package com.fcsservice.controller;

import com.fcsservice.model.pojo.UserAccount;
import com.fcsservice.model.pojo.UserData;
import com.fcsservice.service.AccountService;
import com.fcsservice.service.CodeService;
import com.fcsservice.service.UserDataService;
import com.fcsservice.utils.FcsserviceUtil;
import com.fcsservice.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by YE on 2017/10/31 9:08.
 */

@Controller
@CrossOrigin
@RequestMapping(value="/UserDataController")
public class UserDataController {
    @Autowired
    UserDataService userDataService;
    @Autowired
    AccountService accountService;
    @Autowired
    CodeService codeService;


    @RequestMapping(value="/getUserPage",method = {RequestMethod.POST})
    @ResponseBody
    public Result getUserPage(@RequestParam("userId") String userId){
        Result result = new Result();

        UserAccount userAccount = accountService.getAccountById(userId);
        UserData userData = userDataService.getUserDataByUserId(userId);

        if (userAccount != null){
            Map<String,String> userResult = new HashMap<String, String>();
            userResult.put("userName",userAccount.getUserAccount());
            userResult.put("userType",userAccount.getUserType().toString());
            if (userData != null){
                userResult.put("userPortrait",userData.getDataPortrait());
            }else {
                userResult.put("userPortrait",null);
            }
            result.setCode(Result.SUCCESS);
            result.setObj(userResult);
        }else {
            result.setCode(Result.FAIL);
            result.setMsg("当前用户不存在");
        }
        return result;
    }

    @RequestMapping(value="/uploadImage",method = {RequestMethod.POST})
    @ResponseBody
    public Result uploadImage(@RequestParam("userId") String userId,
                              @RequestParam("image") String base64Data){
        Result result = new Result();
        FcsserviceUtil fcsserviceUtil = new FcsserviceUtil();

        //保存图片路径
        try{
            String fileName = fcsserviceUtil.saveImage(fcsserviceUtil.IMAGEPATH,base64Data);
            if (fileName == null) {
                throw new Exception("图片保存失败");
            }
            UserData userData = userDataService.getUserDataByUserId(userId);
            String oldPortrait = userData.getDataPortrait();
            if (oldPortrait != null){
                File deleteFile = new File(fcsserviceUtil.IMAGEPATH +oldPortrait);
                if (deleteFile.isFile() && deleteFile.exists()) {
                    deleteFile.delete();
                }
            }

            userDataService.addUserPortrait(userId,fileName);

            result.setCode(Result.SUCCESS);
            result.setMsg("上传成功");
        }catch (Exception e) {
            e.printStackTrace();
            result.setCode(Result.FAIL);
            result.setMsg("上传失败,"+e.getMessage());
        }
        return result;
    }

    @RequestMapping(value="/getUserDetail",method = {RequestMethod.POST})
    @ResponseBody
    public Result getUserDetail(@RequestParam("userId") String userId){
        Result result = new Result();

        UserAccount userAccount = accountService.getAccountById(userId);
        UserData userData = userDataService.getUserDataByUserId(userId);

        if (userAccount != null && userData!=null){
            Map<String,String> userResult = new HashMap<String, String>();
            userResult.put("userName",userAccount.getUserAccount());
            userResult.put("userMail",userData.getDataMail());
            userResult.put("userPhone",userData.getDataPhone());

            result.setCode(Result.SUCCESS);
            result.setObj(userResult);
        }else {
            result.setCode(Result.FAIL);
            result.setMsg("当前用户不存在");
        }
        return result;
    }

    @RequestMapping(value="/updateMail",method = {RequestMethod.POST})
    @ResponseBody
    public Result updateMail(@RequestParam("oldMail") String oldMail,
                             @RequestParam("newMail") String newMail){
        Result result = new Result();

        UserData userData = userDataService.getUserByMail(oldMail);
        if (userData != null){
            userData.setDataMail(newMail);
            userDataService.updateUserData(userData);
            result.setCode(Result.SUCCESS);
        }else {
            result.setCode(Result.FAIL);
            result.setMsg("该邮箱未绑定账号");
        }

        return result;
    }

    @RequestMapping(value="/updatePhone",method = {RequestMethod.POST})
    @ResponseBody
    public Result updatePhone(@RequestParam("userId") String userId,
                             @RequestParam("newPhone") String newPhone){
        Result result = new Result();

        UserData userData = userDataService.getUserDataByUserId(userId);
        if (userData != null){
            userData.setDataPhone(newPhone);
            userDataService.updateUserData(userData);
            result.setCode(Result.SUCCESS);
        }else {
            result.setCode(Result.FAIL);
            result.setMsg("该用户不存在");
        }

        return result;
    }

    @RequestMapping(value="/getUserDataToPurchase",method = {RequestMethod.POST})
    @ResponseBody
    public Result getUserDataToPurchase(@RequestParam("userId") String userId){
        Result result = new Result();

        UserData userData = userDataService.getUserDataByUserId(userId);
        UserAccount userAccount = accountService.getAccountById(userId);
        if (userData != null && userAccount != null){
            Map<String,String> map = new HashMap<String, String>();
            map.put("userName",userAccount.getUserAccount());
            map.put("userPhone",userData.getDataPhone());
            map.put("userMail",userData.getDataMail());

            result.setCode(Result.SUCCESS);
            result.setObj(map);
            result.setMsg("获取资料成功");
        }else {
            result.setCode(Result.FAIL);
            result.setMsg("该用户不存在");
        }

        return result;
    }

}
