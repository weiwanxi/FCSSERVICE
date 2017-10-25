package com.fcsservice.model.pojo;

import java.io.Serializable;

public class Follow implements Serializable {
    private String followId;

    private String followerId;

    private String concernId;

    private String followTime;

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

    public String getFollowTime() {
        return followTime;
    }

    public void setFollowTime(String followTime) {
        this.followTime = followTime;
    }
}