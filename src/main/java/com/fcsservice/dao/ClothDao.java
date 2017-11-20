package com.fcsservice.dao;

import com.fcsservice.model.dao.ClothMapper;
import com.fcsservice.model.pojo.Cloth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by YE on 2017/11/19 15:36.
 */

@Repository
public class ClothDao {
    @Autowired
    ClothMapper clothMapper;

    public List<Cloth> getCostumeOrderByComment(int page, int number){
        return clothMapper.selectOrderByComment(page,number);
    }

    public List<Cloth> getCostumeOrderByFabulous(int page, int number){
        return clothMapper.selectOrderByFabulous(page,number);
    }
}
