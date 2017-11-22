package com.fcsservice.model.pojo;

import java.io.Serializable;

public class Dictdata implements Serializable {
    private Integer dactionarydataId;

    private Integer dictionarycategoryId;

    private String dictionarydataValue;

    private static final long serialVersionUID = 1L;

    public Integer getDactionarydataId() {
        return dactionarydataId;
    }

    public void setDactionarydataId(Integer dactionarydataId) {
        this.dactionarydataId = dactionarydataId;
    }

    public Integer getDictionarycategoryId() {
        return dictionarycategoryId;
    }

    public void setDictionarycategoryId(Integer dictionarycategoryId) {
        this.dictionarycategoryId = dictionarycategoryId;
    }

    public String getDictionarydataValue() {
        return dictionarydataValue;
    }

    public void setDictionarydataValue(String dictionarydataValue) {
        this.dictionarydataValue = dictionarydataValue;
    }
}