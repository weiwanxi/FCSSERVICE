package com.fcsservice.model.dao;

import com.fcsservice.model.pojo.Dictdata;

public interface DictdataMapper {
    int deleteByPrimaryKey(Integer dactionarydataId);

    int insert(Dictdata record);

    int insertSelective(Dictdata record);

    Dictdata selectByPrimaryKey(Integer dactionarydataId);

    int updateByPrimaryKeySelective(Dictdata record);

    int updateByPrimaryKey(Dictdata record);
}