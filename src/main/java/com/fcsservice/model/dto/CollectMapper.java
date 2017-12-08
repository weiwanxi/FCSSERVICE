package com.fcsservice.model.dto;

import com.fcsservice.model.pojo.Collect;

import java.util.List;

public interface CollectMapper {
    int deleteByPrimaryKey(String collectId);

    int insert(Collect record);

    int insertSelective(Collect record);

    Collect selectByPrimaryKey(String collectId);

    Collect selectByIU(String informationId,String userId);

    List<Collect> selectByUTDesc(String userId,int type);

    List<Collect> selectByUTAsc(String userId,int type);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);
}