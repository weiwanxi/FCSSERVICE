package com.fcsservice.dao;

import com.fcsservice.model.dto.FabulousMapper;
import com.fcsservice.model.pojo.Fabulous;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by YE on 2017/11/17 23:13.
 */

@Repository
public class FabulousDao {
    @Autowired
    FabulousMapper fabulousMapper;

    public boolean getFabulousType(String informationId,String userId){
        return fabulousMapper.selectByIU(informationId,userId) != null;
    }

    public Fabulous getFabulousByIU(String informationId,String userId){
        return fabulousMapper.selectByIU(informationId,userId);
    }

    public int getFabulous(String informationId){
        return fabulousMapper.selectFabulousNumber(informationId);
    }

    public void addFabulous(Fabulous fabulous){
        fabulousMapper.insertSelective(fabulous);
    }

    public void deleteFabulous(String fabulousId){
        fabulousMapper.deleteByPrimaryKey(fabulousId);
    }

}
