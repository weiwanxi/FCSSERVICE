package com.fcsservice.model.dao;

import com.fcsservice.model.pojo.Costume;

import java.util.List;

public interface CostumeMapper {
    int deleteByPrimaryKey(String costumeId);

    int insert(Costume record);

    int insertSelective(Costume record);

    Costume selectByPrimaryKey(String costumeId);

    List<Costume> selectOrderByComment(int page, int number,int screen);

    List<Costume> selectOrderByFabulous(int page, int number,int screen);

    List<Costume> selectCostumeBySearch(String searchText,int page,int number);

    int updateByPrimaryKeySelective(Costume record);

    int updateByPrimaryKey(Costume record);
}