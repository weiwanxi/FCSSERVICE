package com.fcsservice.dao;

import com.fcsservice.model.dao.CommentMapper;
import com.fcsservice.model.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public List<Comment> getCommentList(String commentaryId){
        return commentMapper.selectByCommentaryId(commentaryId);
    }

    public void saveComment(Comment comment){
        commentMapper.insertSelective(comment);
    }

    public List<Comment> getUserCommentList(String userId){
        return commentMapper.selectByCommentatorId(userId);
    }

    public Comment getComment(String commentId){
        return commentMapper.selectByPrimaryKey(commentId);
    }

    public void deleteAComment(String commentId){
        commentMapper.deleteByPrimaryKey(commentId);
    }

    public void deleteAllCommentByUserId(String userId){
        commentMapper.deleteAllCommentByUserId(userId);
    }

    public void deleteAllCommentByInfoId(String informationId){
        commentMapper.deleteAllCommentByInfoId(informationId);
    }
}
