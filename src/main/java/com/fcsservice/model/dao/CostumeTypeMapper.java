package com.fcsservice.model.dao;

import com.fcsservice.model.pojo.CostumeType;

public interface CostumeTypeMapper {
    int deleteByPrimaryKey(Integer typeId);

    int insert(CostumeType record);

    int insertSelective(CostumeType record);

    CostumeType selectByPrimaryKey(Integer typeId);

    int updateByPrimaryKeySelective(CostumeType record);

    int updateByPrimaryKey(CostumeType record);
}