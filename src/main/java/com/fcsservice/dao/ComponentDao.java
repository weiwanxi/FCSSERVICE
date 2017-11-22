package com.fcsservice.dao;

import com.fcsservice.model.dao.ComponentMapper;
import com.fcsservice.model.pojo.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by YE on 2017/11/22 9:00.
 */

@Repository
public class ComponentDao {
    @Autowired
    ComponentMapper componentMapper;

    public Component getComponentById(int id){
        return componentMapper.selectByPrimaryKey(id);
    }
}
