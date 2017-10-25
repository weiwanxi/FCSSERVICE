package com.fcsservice.model.pojo;

import java.io.Serializable;

public class Component implements Serializable {
    private Integer componentId;

    private Integer componentNumber;

    private String componentSupcategory;

    private String componentName;

    private static final long serialVersionUID = 1L;

    public Integer getComponentId() {
        return componentId;
    }

    public void setComponentId(Integer componentId) {
        this.componentId = componentId;
    }

    public Integer getComponentNumber() {
        return componentNumber;
    }

    public void setComponentNumber(Integer componentNumber) {
        this.componentNumber = componentNumber;
    }

    public String getComponentSupcategory() {
        return componentSupcategory;
    }

    public void setComponentSupcategory(String componentSupcategory) {
        this.componentSupcategory = componentSupcategory;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }
}