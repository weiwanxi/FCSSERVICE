package com.fcsservice.model.dao;

import com.fcsservice.model.pojo.CostumeType;

import java.util.List;

public interface CostumeTypeMapper {
    int deleteByPrimaryKey(Integer typeId);

    int insert(CostumeType record);

    int insertSelective(CostumeType record);

    CostumeType selectByPrimaryKey(Integer typeId);

    List<CostumeType> selectByLevel(Integer typeLevel);

    List<CostumeType> selectBypId(Integer pId);

    int updateByPrimaryKeySelective(CostumeType record);

    int updateByPrimaryKey(CostumeType record);
}