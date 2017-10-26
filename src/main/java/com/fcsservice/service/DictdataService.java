package com.fcsservice.service;

import com.fcsservice.dao.DictdataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by YE on 2017/10/26 15:10.
 */

@Service
@Transactional
public class DictdataService {
    @Autowired
    DictdataDao dictdataDao;

    public int getIdByValue(String value){
        return dictdataDao.getDictDataIdByValue(value);
    }

}
