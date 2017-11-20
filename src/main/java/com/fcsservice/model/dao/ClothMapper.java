package com.fcsservice.model.dao;

import com.fcsservice.model.pojo.Cloth;

import java.util.List;

public interface ClothMapper {
    int deleteByPrimaryKey(String clothId);

    int insert(Cloth record);

    int insertSelective(Cloth record);

    Cloth selectByPrimaryKey(String clothId);

    List<Cloth> selectOrderByComment(int page, int number);

    List<Cloth> selectOrderByFabulous(int page, int number);

    int updateByPrimaryKeySelective(Cloth record);

    int updateByPrimaryKey(Cloth record);
}