package com.fcsservice.model.pojo;

import java.io.Serializable;

public class Weave implements Serializable {
    private Integer weaveId;

    private Integer weaveSupcategory;

    private String weaveName;

    private Integer weaveLevel;

    private static final long serialVersionUID = 1L;

    public Integer getWeaveId() {
        return weaveId;
    }

    public void setWeaveId(Integer weaveId) {
        this.weaveId = weaveId;
    }

    public Integer getWeaveSupcategory() {
        return weaveSupcategory;
    }

    public void setWeaveSupcategory(Integer weaveSupcategory) {
        this.weaveSupcategory = weaveSupcategory;
    }

    public String getWeaveName() {
        return weaveName;
    }

    public void setWeaveName(String weaveName) {
        this.weaveName = weaveName;
    }

    public Integer getWeaveLevel() {
        return weaveLevel;
    }

    public void setWeaveLevel(Integer weaveLevel) {
        this.weaveLevel = weaveLevel;
    }
}