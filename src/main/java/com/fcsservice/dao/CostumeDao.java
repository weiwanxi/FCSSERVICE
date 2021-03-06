package com.fcsservice.dao;

import com.fcsservice.model.dto.CostumeMapper;
import com.fcsservice.model.pojo.Costume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by YE on 2017/11/19 15:36.
 */

@Repository
public class CostumeDao {
    @Autowired
    CostumeMapper costumeMapper;

    public List<Costume> getCostumeOrderByComment(int page, int number,int screen){
        return costumeMapper.selectOrderByComment(page,number,screen);
    }

    public List<Costume> getCostumeOrderByFabulous(int page, int number,int screen){
        return costumeMapper.selectOrderByFabulous(page,number,screen);
    }

    public List<Costume> getCostumeBySearch(String searchText,int page,int number){
        return costumeMapper.selectCostumeBySearch(searchText,page,number);
    }

    public List<Costume> getCostumeByComponent(int page,int number,int component){
        return costumeMapper.selectOrderByComment(page,number,component);
    }

    public Costume getCostumeById(String costumeId){
        return costumeMapper.selectByPrimaryKey(costumeId);
    }

}
