package com.fcsservice.model.dao;

import com.fcsservice.model.pojo.Costume;

public interface CostumeMapper {
    int deleteByPrimaryKey(String costumeId);

    int insert(Costume record);

    int insertSelective(Costume record);

    Costume selectByPrimaryKey(String costumeId);

    int updateByPrimaryKeySelective(Costume record);

    int updateByPrimaryKey(Costume record);
}