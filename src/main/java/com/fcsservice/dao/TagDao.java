package com.fcsservice.dao;

import com.fcsservice.model.dto.TagMapper;
import com.fcsservice.model.pojo.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by YE on 2017/10/26 15:09.
 */

@Repository
public class TagDao {
    @Autowired
    TagMapper tagMapper;

    public void addTag(Tag tag){
        tagMapper.insert(tag);
    }

    public List<String> getUserIdListByTag(String tagId){
        return tagMapper.selectUserIdByTagId(tagId);
    }
}
