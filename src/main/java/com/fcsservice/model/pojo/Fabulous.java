package com.fcsservice.model.pojo;

import java.io.Serializable;

public class Fabulous implements Serializable {
    private String fabulousId;

    private String informationId;

    private String userId;

    private static final long serialVersionUID = 1L;

    public String getFabulousId() {
        return fabulousId;
    }

    public void setFabulousId(String fabulousId) {
        this.fabulousId = fabulousId;
    }

    public String getInformationId() {
        return informationId;
    }

    public void setInformationId(String informationId) {
        this.informationId = informationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}