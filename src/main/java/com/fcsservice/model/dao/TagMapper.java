package com.fcsservice.model.dao;

import com.fcsservice.model.pojo.Tag;

public interface TagMapper {
    int deleteByPrimaryKey(String userId);

    int insert(Tag record);

    int insertSelective(Tag record);

    Tag selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);
}