package com.fcsservice.service;

import com.fcsservice.dao.TagDao;
import com.fcsservice.model.pojo.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by YE on 2017/10/26 15:08.
 */

@Service
@Transactional
public class TagService {
    @Autowired
    TagDao tagDao;

    public void addTag(String userId,int tagId) {
        Tag tag = new Tag();
        tag.setUserId(userId);
        tag.setTagId(tagId);
        tagDao.addTag(tag);
    }
}
