package com.fcsservice.model.pojo;

import java.io.Serializable;

public class CostumeType implements Serializable {
    private Integer typeId;

    private Integer typeSupcategpry;

    private String typeName;

    private Integer typeLevel;

    private static final long serialVersionUID = 1L;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getTypeSupcategpry() {
        return typeSupcategpry;
    }

    public void setTypeSupcategpry(Integer typeSupcategpry) {
        this.typeSupcategpry = typeSupcategpry;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getTypeLevel() {
        return typeLevel;
    }

    public void setTypeLevel(Integer typeLevel) {
        this.typeLevel = typeLevel;
    }
}