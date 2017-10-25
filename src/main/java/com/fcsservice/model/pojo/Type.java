package com.fcsservice.model.pojo;

import java.io.Serializable;

public class Type implements Serializable {
    private Integer typeId;

    private Integer typeNumber;

    private String typeSupcategpry;

    private String typeName;

    private static final long serialVersionUID = 1L;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getTypeNumber() {
        return typeNumber;
    }

    public void setTypeNumber(Integer typeNumber) {
        this.typeNumber = typeNumber;
    }

    public String getTypeSupcategpry() {
        return typeSupcategpry;
    }

    public void setTypeSupcategpry(String typeSupcategpry) {
        this.typeSupcategpry = typeSupcategpry;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}