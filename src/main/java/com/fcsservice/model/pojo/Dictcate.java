package com.fcsservice.model.pojo;

import java.io.Serializable;

public class Dictcate implements Serializable {
    private Integer dictionarycategoryId;

    private Integer dictionarycategoryNumber;

    private String dictionarycategoryName;

    private static final long serialVersionUID = 1L;

    public Integer getDictionarycategoryId() {
        return dictionarycategoryId;
    }

    public void setDictionarycategoryId(Integer dictionarycategoryId) {
        this.dictionarycategoryId = dictionarycategoryId;
    }

    public Integer getDictionarycategoryNumber() {
        return dictionarycategoryNumber;
    }

    public void setDictionarycategoryNumber(Integer dictionarycategoryNumber) {
        this.dictionarycategoryNumber = dictionarycategoryNumber;
    }

    public String getDictionarycategoryName() {
        return dictionarycategoryName;
    }

    public void setDictionarycategoryName(String dictionarycategoryName) {
        this.dictionarycategoryName = dictionarycategoryName;
    }
}