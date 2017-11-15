package com.fcsservice.model.pojo;

import java.io.Serializable;
import java.util.Date;

public class Follow implements Serializable {
    private String followId;

    private String followerId;

    private String concernId;

    private Date followTime;

    private static final long serialVersionUID = 1L;

    public String getFollowId() {
        return followId;
    }

    public void setFollowId(String followId) {
        this.followId = followId;
    }

    public String getFollowerId() {
        return followerId;
    }

    public void setFollowerId(String followerId) {
        this.followerId = followerId;
    }

    public String getConcernId() {
        return concernId;
    }

    public void setConcernId(String concernId) {
        this.concernId = concernId;
    }

    public Date getFollowTime() {
        return followTime;
    }

    public void setFollowTime(Date followTime) {
        this.followTime = followTime;
    }
}