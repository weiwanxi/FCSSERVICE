package com.fcsservice.service;

import com.fcsservice.dao.DictcateDao;
import com.fcsservice.model.pojo.Dictcate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by YE on 2017/11/8 22:39.
 */

@Service
@Transactional
public class DictcateService {
    @Autowired
    DictcateDao dictcateDao;

    public Dictcate getByValue(String value){
        return dictcateDao.getByValue(value);
    }
}
