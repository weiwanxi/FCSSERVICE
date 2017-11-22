package com.fcsservice.dao;

import com.fcsservice.model.dao.WeaveMapper;
import com.fcsservice.model.pojo.Weave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by YE on 2017/11/22 9:02.
 */

@Repository
public class WeaveDao {
    @Autowired
    WeaveMapper weaveMapper;

    public Weave getWeaveById(int id){
        return weaveMapper.selectByPrimaryKey(id);
    }
}
