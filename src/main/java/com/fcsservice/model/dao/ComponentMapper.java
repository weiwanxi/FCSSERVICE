package com.fcsservice.model.dao;

import com.fcsservice.model.pojo.Component;

public interface ComponentMapper {
    int deleteByPrimaryKey(Integer componentId);

    int insert(Component record);

    int insertSelective(Component record);

    Component selectByPrimaryKey(Integer componentId);

    int updateByPrimaryKeySelective(Component record);

    int updateByPrimaryKey(Component record);
}