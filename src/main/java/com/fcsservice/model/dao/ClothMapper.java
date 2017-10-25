package com.fcsservice.model.dao;

import com.fcsservice.model.pojo.Cloth;

public interface ClothMapper {
    int deleteByPrimaryKey(String clothId);

    int insert(Cloth record);

    int insertSelective(Cloth record);

    Cloth selectByPrimaryKey(String clothId);

    int updateByPrimaryKeySelective(Cloth record);

    int updateByPrimaryKey(Cloth record);
}