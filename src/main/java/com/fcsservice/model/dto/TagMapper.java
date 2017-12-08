package com.fcsservice.model.dto;

import com.fcsservice.model.pojo.Tag;

import java.util.List;

public interface TagMapper {
    int deleteByPrimaryKey(String userId);

    int insert(Tag record);

    int insertSelective(Tag record);

    Tag selectByPrimaryKey(String userId);

    List<String> selectUserIdByTagId(String userId);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);
}