package com.fcsservice.model.dto;

import com.fcsservice.model.pojo.Fabulous;

public interface FabulousMapper {
    int deleteByPrimaryKey(String fabulousId);

    int insert(Fabulous record);

    int insertSelective(Fabulous record);

    Fabulous selectByPrimaryKey(String fabulousId);

    Fabulous selectByIU(String informationId,String userId);

    int selectFabulousNumber(String informationId);

    int updateByPrimaryKeySelective(Fabulous record);

    int updateByPrimaryKey(Fabulous record);
}