package com.fcsservice.service;

import com.fcsservice.dao.AccountDao;
import com.fcsservice.dao.FabulousDao;
import com.fcsservice.dao.WorkDao;
import com.fcsservice.model.pojo.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YE on 2017/11/19 15:34.
 */

@Service
@Transactional
public class WorkService {
    @Autowired
    WorkDao workDao;
    @Autowired
    FabulousDao fabulousDao;
    @Autowired
    AccountDao accountDao;

    public Map<String,String[]> getWorkOrderByComment(int page, int number){
        List<Work> workList = workDao.getCostumeOrderByComment(page,number);

        return getMap(workList,number);
    }

    public Map<String,String[]> getWorkOrderByFabulous(int page, int number){
        List<Work> workList = workDao.getCostumeOrderByFabulous(page,number);

        return getMap(workList,number);
    }

    private Map<String,String[]> getMap(List<Work> workList,int number){
        Map<String,String[]> map = new HashMap<String, String[]>();
        String[] id = new String[number];
        String[] image = new String[number];
        String[] title = new String[number];
        String[] designer = new String[number];
        String[] fabulous = new String[number];

        if (workList != null){
            for (int i=0;i<workList.size();i++){
                id[i] = workList.get(i).getWorkId();
                image[i] = workList.get(i).getWorkPicture1();
                title[i] = workList.get(i).getWorkName();
                designer[i] = accountDao.getUserAccountById(workList.get(i).getDesignerId()).getUserAccount();
                fabulous[i] = fabulousDao.getFabulous(workList.get(i).getWorkId())+"";
            }
            map.put("id",id);
            map.put("image",image);
            map.put("title",title);
            map.put("designer",designer);
            map.put("fabulous",fabulous);
        }else {
            return null;
        }
        return map;
    }
}
