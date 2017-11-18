package com.fcsservice.dao;

import com.fcsservice.model.dao.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by YE on 2017/11/17 23:34.
 */

@Repository
public class CommentDao {
    @Autowired
    CommentMapper commentMapper;

    public int getCommentNumber(String informationId){
        return commentMapper.selectCommentNumber(informationId);
    }
}
