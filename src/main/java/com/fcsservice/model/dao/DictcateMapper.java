package com.fcsservice.model.dao;

import com.fcsservice.model.pojo.Dictcate;

public interface DictcateMapper {
    int deleteByPrimaryKey(Integer dictionarycategoryId);

    int insert(Dictcate record);

    int insertSelective(Dictcate record);

    Dictcate selectByPrimaryKey(Integer dictionarycategoryId);

    Dictcate selectByValue(String dictionarycategoryName);

    int updateByPrimaryKeySelective(Dictcate record);

    int updateByPrimaryKey(Dictcate record);
}