package com.fcsservice.dao;

import com.fcsservice.model.dto.CostumeTypeMapper;
import com.fcsservice.model.pojo.CostumeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by YE on 2017/11/22 9:10.
 */

@Repository
public class CostumeTypeDao {
    @Autowired
    CostumeTypeMapper costumeTypeMapper;

    public CostumeType getCostumeTypeById(int id){
        return costumeTypeMapper.selectByPrimaryKey(id);
    }

    public List<CostumeType> getCostumeListByLevel(int level){
        return costumeTypeMapper.selectByLevel(level);
    }

    public List<CostumeType> getCostumeListByPId(int pId){
        return costumeTypeMapper.selectBypId(pId);
    }
}
