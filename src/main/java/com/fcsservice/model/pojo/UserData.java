package com.fcsservice.model.pojo;

import java.io.Serializable;

public class UserData implements Serializable {
    private String dataId;

    private String userId;

    private String dataPortrait;

    private String dataMail;

    private String dataPhone;

    private Integer designtypeId;

    private static final long serialVersionUID = 1L;

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDataPortrait() {
        return dataPortrait;
    }

    public void setDataPortrait(String dataPortrait) {
        this.dataPortrait = dataPortrait;
    }

    public String getDataMail() {
        return dataMail;
    }

    public void setDataMail(String dataMail) {
        this.dataMail = dataMail;
    }

    public String getDataPhone() {
        return dataPhone;
    }

    public void setDataPhone(String dataPhone) {
        this.dataPhone = dataPhone;
    }

    public Integer getDesigntypeId() {
        return designtypeId;
    }

    public void setDesigntypeId(Integer designtypeId) {
        this.designtypeId = designtypeId;
    }
}