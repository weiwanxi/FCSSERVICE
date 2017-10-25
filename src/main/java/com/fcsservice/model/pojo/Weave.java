package com.fcsservice.model.pojo;

import java.io.Serializable;

public class Weave implements Serializable {
    private Integer weaveId;

    private Integer weaveNumber;

    private String weaveSupcategory;

    private String weaveName;

    private static final long serialVersionUID = 1L;

    public Integer getWeaveId() {
        return weaveId;
    }

    public void setWeaveId(Integer weaveId) {
        this.weaveId = weaveId;
    }

    public Integer getWeaveNumber() {
        return weaveNumber;
    }

    public void setWeaveNumber(Integer weaveNumber) {
        this.weaveNumber = weaveNumber;
    }

    public String getWeaveSupcategory() {
        return weaveSupcategory;
    }

    public void setWeaveSupcategory(String weaveSupcategory) {
        this.weaveSupcategory = weaveSupcategory;
    }

    public String getWeaveName() {
        return weaveName;
    }

    public void setWeaveName(String weaveName) {
        this.weaveName = weaveName;
    }
}