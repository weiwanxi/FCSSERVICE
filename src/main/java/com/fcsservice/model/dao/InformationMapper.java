package com.fcsservice.model.dao;

import com.fcsservice.model.pojo.Information;

public interface InformationMapper {
    int deleteByPrimaryKey(String informationId);

    int insert(Information record);

    int insertSelective(Information record);

    Information selectByPrimaryKey(String informationId);

    int updateByPrimaryKeySelective(Information record);

    int updateByPrimaryKey(Information record);
}