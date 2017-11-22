package com.fcsservice.model.dao;

import com.fcsservice.model.pojo.Dictdata;

import java.util.List;

public interface DictdataMapper {
    int deleteByPrimaryKey(Integer dactionarydataId);

    int insert(Dictdata record);

    int insertSelective(Dictdata record);

    Dictdata selectByPrimaryKey(Integer dactionarydataId);

    Dictdata selectByValue(String dictionarydata_value);

    List<Dictdata> selectByDatecateId(int datecateId);

    int updateByPrimaryKeySelective(Dictdata record);

    int updateByPrimaryKey(Dictdata record);
}