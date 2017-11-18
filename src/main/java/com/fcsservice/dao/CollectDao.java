package com.fcsservice.dao;

import com.fcsservice.model.dao.CollectMapper;
import com.fcsservice.model.pojo.Collect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by YE on 2017/11/17 23:38.
 */

@Repository
public class CollectDao {
    @Autowired
    CollectMapper collectMapper;

    public boolean getCollectType(String informationId,String userId){
        return collectMapper.selectByIU(informationId,userId) != null;
    }

    public Collect getCollectByIU(String informationId,String userId){
        return collectMapper.selectByIU(informationId,userId);
    }

    public void addCollect(Collect collect){
        collectMapper.insertSelective(collect);
    }

    public void deleteCollect(String collectId){
        collectMapper.deleteByPrimaryKey(collectId);
    }
}
