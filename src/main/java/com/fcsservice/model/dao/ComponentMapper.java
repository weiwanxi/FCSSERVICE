package com.fcsservice.model.dao;

import com.fcsservice.model.pojo.Component;

import java.util.List;

public interface ComponentMapper {
    int deleteByPrimaryKey(Integer componentId);

    int insert(Component record);

    int insertSelective(Component record);

    Component selectByPrimaryKey(Integer componentId);

    List<Component> selectByLevel(Integer componentLevel);

    List<Component> selectByPId(Integer pId);

    int updateByPrimaryKeySelective(Component record);

    int updateByPrimaryKey(Component record);
}