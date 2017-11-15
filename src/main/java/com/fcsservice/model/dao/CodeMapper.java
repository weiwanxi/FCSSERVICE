package com.fcsservice.model.dao;

import com.fcsservice.model.pojo.Code;

public interface CodeMapper {
    int insert(Code record);

    int insertSelective(Code record);

    Code selectByMail(String mail,int typee);

    void deleteByMail(String mail,int typee);
}