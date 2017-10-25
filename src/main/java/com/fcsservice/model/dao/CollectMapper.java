package com.fcsservice.model.dao;

import com.fcsservice.model.pojo.Collect;

public interface CollectMapper {
    int deleteByPrimaryKey(String collectId);

    int insert(Collect record);

    int insertSelective(Collect record);

    Collect selectByPrimaryKey(String collectId);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);
}