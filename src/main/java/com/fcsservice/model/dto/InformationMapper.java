package com.fcsservice.model.dto;

import com.fcsservice.model.pojo.Information;

import java.util.List;

public interface InformationMapper {
    int deleteByPrimaryKey(String informationId);

    int insert(Information record);

    int insertSelective(Information record);

    Information selectByPrimaryKey(String informationId);

    List<Information> selectByStatus(int information_status);

    List<Information> selectInformationList();

    List<Information> selectNewInformationList(String information_reltime);

    List<Information> selectOldInformationList(String information_reltime);

    List<Information> selectInformationBySearch(String searchText,int page,int number);

    int updateByPrimaryKeySelective(Information record);

    int updateByPrimaryKey(Information record);
}