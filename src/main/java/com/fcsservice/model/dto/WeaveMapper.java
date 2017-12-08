package com.fcsservice.model.dto;

import com.fcsservice.model.pojo.Weave;

public interface WeaveMapper {
    int deleteByPrimaryKey(Integer weaveId);

    int insert(Weave record);

    int insertSelective(Weave record);

    Weave selectByPrimaryKey(Integer weaveId);

    int updateByPrimaryKeySelective(Weave record);

    int updateByPrimaryKey(Weave record);
}