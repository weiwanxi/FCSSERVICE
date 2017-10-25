package com.fcsservice.model.dao;

import com.fcsservice.model.pojo.Fabulous;

public interface FabulousMapper {
    int deleteByPrimaryKey(String fabulousId);

    int insert(Fabulous record);

    int insertSelective(Fabulous record);

    Fabulous selectByPrimaryKey(String fabulousId);

    int updateByPrimaryKeySelective(Fabulous record);

    int updateByPrimaryKey(Fabulous record);
}