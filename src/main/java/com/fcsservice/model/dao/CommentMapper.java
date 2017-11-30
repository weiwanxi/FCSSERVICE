package com.fcsservice.model.dao;

import com.fcsservice.model.pojo.Comment;

import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(String commentId);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(String commentId);

    int selectCommentNumber(String commentary_id);

    List<Comment> selectByCommentaryId(String commentaryId);

    List<Comment> selectByCommentatorId(String commentatorId);

    void deleteAllCommentByUserId(String userId);

    void deleteAllCommentByInfoId(String informationId);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
}