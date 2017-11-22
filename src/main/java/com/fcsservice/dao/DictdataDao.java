package com.fcsservice.dao;

import com.fcsservice.model.dao.DictdataMapper;
import com.fcsservice.model.pojo.Dictdata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by YE on 2017/10/26 15:11.
 */

@Repository
public class DictdataDao {
    @Autowired
    DictdataMapper dictdataMapper;

    public Dictdata getDictDataById(int id){
        return dictdataMapper.selectByPrimaryKey(id);
    }

    public int getDictDataIdByValue(String value){
        Dictdata dictdata = dictdataMapper.selectByValue(value);
        if (dictdata != null)
            return dictdata.getDactionarydataId();
        else
            return -1;
    }

    public List<Dictdata> getByDatecateId(int datecateId){
        return dictdataMapper.selectByDatecateId(datecateId);
    }
}
