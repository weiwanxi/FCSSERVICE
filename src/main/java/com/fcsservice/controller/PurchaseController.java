package com.fcsservice.controller;

import com.fcsservice.service.PurchaseService;
import com.fcsservice.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by YE on 2017/11/27 21:35.
 */

@Controller
@CrossOrigin
@RequestMapping(value="/PurchaseController")
public class PurchaseController {
    @Autowired
    PurchaseService purchaseService;

    @RequestMapping(value="/addPurchase",method = {RequestMethod.POST})
    @ResponseBody
    public Result addPurchase(@RequestParam("userId") String userId,
                              @RequestParam("workId") String workId,
                              @RequestParam("designerId") String designerId,
                              @RequestParam("purchaseRequest") String purchaseRequest,
                              @RequestParam("purchaseName") String purchaseName,
                              @RequestParam("purchasePhone") String purchasePhone,
                              @RequestParam("purchaseMail") String purchaseMail){
        Result result = new Result();

        String msg = purchaseService.addPurchase(userId,workId,designerId,purchaseRequest,
                purchaseName,purchasePhone,purchaseMail);

        if (msg == null){
            result.setCode(Result.SUCCESS);
            result.setMsg("发送购买意向成功");
        }else {
            result.setCode(Result.FAIL);
            result.setMsg(msg);
        }

        return result;
    }

    @RequestMapping(value="/getUserBuy",method = {RequestMethod.POST})
    @ResponseBody
    public Result getUserBuy(@RequestParam("userId") String userId){
        Result result = new Result();

        Map<String,String[]> map = purchaseService.getUserBuy(userId);
        if (map != null){
            result.setCode(Result.SUCCESS);
            result.setObj(map);
            result.setMsg("获取购买记录成功");
        }else {
            result.setCode(Result.FAIL);
            result.setMsg("无购买记录");
        }

        return result;
    }

    @RequestMapping(value="/getPurchaseDetail",method = {RequestMethod.POST})
    @ResponseBody
    public Result getPurchaseDetail(@RequestParam("purchaseId") String purchaseId){
        Result result = new Result();

        Map<String,String> map = purchaseService.getPurchaseDetail(purchaseId);
        if (map != null){
            result.setCode(Result.SUCCESS);
            result.setObj(map);
            result.setMsg("获取购买记录成功");
        }else {
            result.setCode(Result.FAIL);
            result.setMsg("无购买记录");
        }

        return result;
    }

    @RequestMapping(value="/getUserSell",method = {RequestMethod.POST})
    @ResponseBody
    public Result getUserSell(@RequestParam("userId") String userId){
        Result result = new Result();

        Map<String,String[]> map = purchaseService.getUserSell(userId);
        if (map != null){
            result.setCode(Result.SUCCESS);
            result.setObj(map);
            result.setMsg("获取购买意向成功");
        }else {
            result.setCode(Result.FAIL);
            result.setMsg("无购买意向");
        }

        return result;
    }

    @RequestMapping(value="/getSellDetail",method = {RequestMethod.POST})
    @ResponseBody
    public Result getSellDetail(@RequestParam("sellId") String sellId){
        Result result = new Result();

        Map<String,String> map = purchaseService.getSellDetail(sellId);
        if (map != null){
            result.setCode(Result.SUCCESS);
            result.setObj(map);
            result.setMsg("获取购买意向成功");
        }else {
            result.setCode(Result.FAIL);
            result.setMsg("无购买意向");
        }

        return result;
    }

    @RequestMapping(value="/setReaded",method = {RequestMethod.POST})
    @ResponseBody
    public Result setReaded(@RequestParam("purchaseId") String purchaseId){
        Result result = new Result();

        boolean resultt = purchaseService.setReaded(purchaseId);
        if (resultt){
            result.setCode(Result.SUCCESS);
            result.setMsg("成功设置为已读");
        }else {
            result.setCode(Result.FAIL);
            result.setMsg("此购买意向不存在或已读");
        }

        return result;
    }

    @RequestMapping(value="/getUnRead",method = {RequestMethod.POST})
    @ResponseBody
    public Result getUnRead(@RequestParam("userId") String userId){
        Result result = new Result();

        int count = purchaseService.getUnRead(userId);

        result.setCode(Result.SUCCESS);
        result.setObj(count);

        return result;
    }

    @RequestMapping(value="/deleteAPurchase",method = {RequestMethod.POST})
    @ResponseBody
    public Result deleteAPurchase(@RequestParam("purchaseId") String purchaseId){
        Result result = new Result();

        purchaseService.deleteAPurchase(purchaseId);

        result.setCode(Result.SUCCESS);
        return result;
    }

    @RequestMapping(value="/deleteAllPurchase",method = {RequestMethod.POST})
    @ResponseBody
    public Result deleteAllPurchase(@RequestParam("userId") String userId){
        Result result = new Result();

        purchaseService.deleteAllPurchase(userId);

        result.setCode(Result.SUCCESS);
        return result;
    }

    @RequestMapping(value="/deleteASell",method = {RequestMethod.POST})
    @ResponseBody
    public Result deleteASell(@RequestParam("purchaseId") String purchaseId){
        Result result = new Result();

        purchaseService.deleteASell(purchaseId);

        result.setCode(Result.SUCCESS);
        return result;
    }

    @RequestMapping(value="/deleteAllSell",method = {RequestMethod.POST})
    @ResponseBody
    public Result deleteAllSell(@RequestParam("userId") String userId){
        Result result = new Result();

        purchaseService.deleteAllSell(userId);

        result.setCode(Result.SUCCESS);
        return result;
    }
}
