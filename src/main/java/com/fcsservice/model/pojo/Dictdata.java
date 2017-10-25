package com.fcsservice.model.pojo;

import java.io.Serializable;

public class Dictdata implements Serializable {
    private Integer dactionarydataId;

    private Integer dictionarycategoryNumber;

    private Integer dictionarydataNumber;

    private String dictionarydataValue;

    private static final long serialVersionUID = 1L;

    public Integer getDactionarydataId() {
        return dactionarydataId;
    }

    public void setDactionarydataId(Integer dactionarydataId) {
        this.dactionarydataId = dactionarydataId;
    }

    public Integer getDictionarycategoryNumber() {
        return dictionarycategoryNumber;
    }

    public void setDictionarycategoryNumber(Integer dictionarycategoryNumber) {
        this.dictionarycategoryNumber = dictionarycategoryNumber;
    }

    public Integer getDictionarydataNumber() {
        return dictionarydataNumber;
    }

    public void setDictionarydataNumber(Integer dictionarydataNumber) {
        this.dictionarydataNumber = dictionarydataNumber;
    }

    public String getDictionarydataValue() {
        return dictionarydataValue;
    }

    public void setDictionarydataValue(String dictionarydataValue) {
        this.dictionarydataValue = dictionarydataValue;
    }
}