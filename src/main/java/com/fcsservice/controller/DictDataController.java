package com.fcsservice.controller;

import com.fcsservice.model.pojo.Dictcate;
import com.fcsservice.model.pojo.Dictdata;
import com.fcsservice.service.DictcateService;
import com.fcsservice.service.DictdataService;
import com.fcsservice.utils.FcsserviceUtil;
import com.fcsservice.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
