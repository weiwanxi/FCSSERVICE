package com.fcsservice.dao;

import com.fcsservice.model.dto.DictcateMapper;
import com.fcsservice.model.pojo.Dictcate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by YE on 2017/11/8 22:34.
 */

@Repository
public class DictcateDao {
    @Autowired
    DictcateMapper dictcateMapper;

    public Dictcate getByValue(String value){
        return dictcateMapper.selectByValue(value);
    }

}
