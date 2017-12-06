package com.fcsservice.controller;

import com.alibaba.fastjson.JSON;
import com.fcsservice.model.pojo.CostumeType;
import com.fcsservice.model.pojo.Dictcate;
import com.fcsservice.model.pojo.Dictdata;
import com.fcsservice.service.ComponentService;
import com.fcsservice.service.CostumeTypeService;
import com.fcsservice.service.DictcateService;
import com.fcsservice.service.DictdataService;
import com.fcsservice.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YE on 2017/11/8 22:32.
 */

@Controller
@CrossOrigin
@RequestMapping(value="/DictDataController")
public class DictDataController {
    @Autowired
    DictcateService dictcateService;
    @Autowired
    DictdataService dictdataService;
    @Autowired
    CostumeTypeService costumeTypeService;
    @Autowired
    ComponentService componentService;

    /**
     * 获取设计标签列表
     * @return tagList|获取标签列表失败
     */
    @RequestMapping(value="/getTagList",method = {RequestMethod.POST})
    @ResponseBody
    public Result getTagList() {
        Result result = new Result();

        Dictcate dictcate = dictcateService.getByValue(FcsserviceUtil.DESIGN_TAG);

        List<Dictdata> dictdataList = dictdataService.getByDatecateId(dictcate.getDictionarycategoryId());

        Map<String,Integer> tagList = new HashMap<String, Integer>();
        for (int i=0; i<dictdataList.size(); i++){
            tagList.put(dictdataList.get(i).getDictionarydataValue(),dictdataList.get(i).getDactionarydataId());
        }

        if (tagList.size() != 0){
            result.setCode(Result.SUCCESS);
            result.setObj(tagList);
        }else {
            result.setCode(Result.FAIL);
            result.setMsg("获取标签列表失败");
        }
        return result;
    }


    /**
     * 获取筛选设计的标签列表
     * @return 标签列表|获取标签列表失败
     */
    @RequestMapping(value="/getSelectTagList",method = {RequestMethod.POST})
    @ResponseBody
    public Result getSelectTagList() {
        Result result = new Result();

        Dictcate dictcate = dictcateService.getByValue(FcsserviceUtil.DESIGN_TAG);

        List<Dictdata> dictdataList = dictdataService.getByDatecateId(dictcate.getDictionarycategoryId());

        Map<String,String[]> tag = new HashMap<String,String[]>();

        String[] tagArray = new String[dictdataList.size()];
        String[] valueArray = new String[dictdataList.size()];
        for (int i=0; i<dictdataList.size(); i++){
            tagArray[i] = dictdataList.get(i).getDictionarydataValue().substring(0,2);
            valueArray[i] = dictdataList.get(i).getDactionarydataId()+"";
        }

        if (tagArray.length != 0 && valueArray.length != 0){
            tag.put("tag",tagArray);
            tag.put("value",valueArray);
            result.setCode(Result.SUCCESS);
            result.setObj(tag);
        }else {
            result.setCode(Result.FAIL);
            result.setMsg("获取标签列表失败");
        }
        return result;
    }


    /**
     * 获取用于筛选的服装类别
     * @return node1List|获取数据失败
     */
    @RequestMapping(value="/getScreenCostumeType",method = {RequestMethod.POST})
    @ResponseBody
    public Result getScreenCostumeType(){
        Result result = new Result();

        List<Node1> node1List = costumeTypeService.getScreenCostumeTypeData();

        if (node1List != null && node1List.size()>0){
            Object obj = JSON.toJSON(node1List);
            result.setCode(Result.SUCCESS);
            result.setObj(obj);
        }else {
            result.setCode(Result.FAIL);
            result.setMsg("获取数据失败");
        }

        return result;
    }


    /**
     * 获取用于筛选的面料列表
     * @return node1List|获取数据失败
     */
    @RequestMapping(value="/getScreenComponent",method = {RequestMethod.POST})
    @ResponseBody
    public Result getScreenComponent(){
        Result result = new Result();

        List<Node2> node1List = componentService.getScreenComponentData();

        if (node1List != null && node1List.size()>0){
            Object obj = JSON.toJSON(node1List);
            result.setCode(Result.SUCCESS);
            result.setObj(obj);
        }else {
            result.setCode(Result.FAIL);
            result.setMsg("获取数据失败");
        }

        return result;
    }


    /**
     * 获取用于筛选的风格列表
     * @return node1List|获取数据失败
     */
    @RequestMapping(value="/getStyle",method = {RequestMethod.POST})
    @ResponseBody
    public Result getWorkStyle(){
        Result result = new Result();

        List<Node3> node1List = dictdataService.getStyleData();

        if (node1List != null && node1List.size()>0){
            Object obj = JSON.toJSON(node1List);
            result.setCode(Result.SUCCESS);
            result.setObj(obj);
        }else {
            result.setCode(Result.FAIL);
            result.setMsg("获取数据失败");
        }

        return result;
    }


    /**
     * 获取用于筛选的款式列表
     * @return node1List|获取数据失败
     */
    @RequestMapping(value="/getModel",method = {RequestMethod.POST})
    @ResponseBody
    public Result getModel(){
        Result result = new Result();

        List<Node3> node1List = dictdataService.getModelData();

        if (node1List != null && node1List.size()>0){
            Object obj = JSON.toJSON(node1List);
            result.setCode(Result.SUCCESS);
            result.setObj(obj);
        }else {
            result.setCode(Result.FAIL);
            result.setMsg("获取数据失败");
        }

        return result;
    }


    /**
     * 获取用于新增作品时的选择列表
     * @return
     */
    @RequestMapping(value="/getWorkPickerData",method = {RequestMethod.POST})
    @ResponseBody
    public Result getWorkPickerData(){
        Result result = new Result();

        List<Node1> workTypeList = costumeTypeService.getCostumeTypeData();
        List<Node2> componentList = componentService.getComponentData();
        List<Node3> styleList = dictdataService.getStyleData();
        List<Node3> modelList = dictdataService.getModelData();

        if (workTypeList != null && workTypeList.size()>0 &&
                componentList != null && componentList.size()>0 &&
                styleList != null && styleList.size()>0 &&
                modelList != null && modelList.size()>0){

            Object obj = JSON.toJSON(workTypeList);
            Object obj1 = JSON.toJSON(componentList);
            Object obj2 = JSON.toJSON(styleList);
            Object obj3 = JSON.toJSON(modelList);

            result.setCode(Result.SUCCESS);
            result.setObj(obj);
            result.setObj1(obj1);
            result.setObj2(obj2);
            result.setObj3(obj3);
        }else {
            result.setCode(Result.FAIL);
            result.setMsg("获取数据失败");
        }

        return result;
    }
}
