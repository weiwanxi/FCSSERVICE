package com.fcsservice.model.pojo;

import java.io.Serializable;

public class Component implements Serializable {
    private Integer componentId;

    private Integer componentSupcategory;

    private String componentName;

    private Integer componentLevel;

    private static final long serialVersionUID = 1L;

    public Integer getComponentId() {
        return componentId;
    }

    public void setComponentId(Integer componentId) {
        this.componentId = componentId;
    }

    public Integer getComponentSupcategory() {
        return componentSupcategory;
    }

    public void setComponentSupcategory(Integer componentSupcategory) {
        this.componentSupcategory = componentSupcategory;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public Integer getComponentLevel() {
        return componentLevel;
    }

    public void setComponentLevel(Integer componentLevel) {
        this.componentLevel = componentLevel;
    }
}