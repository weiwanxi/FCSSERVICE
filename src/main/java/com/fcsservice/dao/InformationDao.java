package com.fcsservice.dao;

import com.fcsservice.model.dto.InformationMapper;
import com.fcsservice.model.pojo.Information;
import com.fcsservice.utils.FcsserviceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by YE on 2017/11/10 16:19.
 */

@Repository
public class InformationDao {
    @Autowired
    InformationMapper informationMapper;

    public List<Information> getTopInformation(){
        return informationMapper.selectByStatus(FcsserviceUtil.TOP_INFORMATION);
    }

    public List<Information> getInformationList(){
        return informationMapper.selectInformationList();
    }

    public List<Information> getNewInformationList(String newInformationID){
        Information information = informationMapper.selectByPrimaryKey(newInformationID);
        if (information != null){
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = format.format(information.getInformationReltime());
            return informationMapper.selectNewInformationList(time);
        }else{
            return null;
        }
    }

    public List<Information> getOldInformationList(String oldInformationID){
        Information information = informationMapper.selectByPrimaryKey(oldInformationID);
        if (information != null){
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = format.format(information.getInformationReltime());
            return informationMapper.selectOldInformationList(time);
        }else{
            return null;
        }
    }

    public Information getInformationById(String informationId){
        return informationMapper.selectByPrimaryKey(informationId);
    }

    public List<Information> getInformationBySearch(String searchText,int page,int number){
        return informationMapper.selectInformationBySearch(searchText,page,number);
    }

}
