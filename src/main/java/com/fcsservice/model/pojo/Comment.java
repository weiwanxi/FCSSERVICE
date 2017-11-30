package com.fcsservice.model.pojo;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {
    private String commentId;

    private String commentaryId;

    private Integer commenttaryType;

    private String commentContent;

    private String commentatorId;

    private Date commentReltime;

    private static final long serialVersionUID = 1L;

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getCommentaryId() {
        return commentaryId;
    }

    public void setCommentaryId(String commentaryId) {
        this.commentaryId = commentaryId;
    }

    public Integer getCommenttaryType() {
        return commenttaryType;
    }

    public void setCommenttaryType(Integer commenttaryType) {
        this.commenttaryType = commenttaryType;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCommentatorId() {
        return commentatorId;
    }

    public void setCommentatorId(String commentatorId) {
        this.commentatorId = commentatorId;
    }

    public Date getCommentReltime() {
        return commentReltime;
    }

    public void setCommentReltime(Date commentReltime) {
        this.commentReltime = commentReltime;
    }
}