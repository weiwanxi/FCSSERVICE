package com.fcsservice.dao;

import com.fcsservice.model.dto.ClothMapper;
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

    public List<Cloth> getClothOrderByComment(int page, int number,int screen){
        return clothMapper.selectOrderByComment(page,number,screen);
    }

    public List<Cloth> getClothOrderByFabulous(int page, int number,int screen){
        return clothMapper.selectOrderByFabulous(page,number,screen);
    }

    public List<Cloth> getClothBySearch(String searchText,int page,int number){
        return clothMapper.selectClothBySearch(searchText,page,number);
    }

    public Cloth getClothById(String clothId){
        return clothMapper.selectByPrimaryKey(clothId);
    }
}
