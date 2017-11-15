package com.fcsservice.model.pojo;

import java.io.Serializable;
import java.util.Date;

public class Information implements Serializable {
    private String informationId;

    private String informationTopic;

    private String informationAuthor;

    private String informationContent;

    private Integer informationFabulous;

    private Date informationReltime;

    private Integer informationStatus;

    private static final long serialVersionUID = 1L;

    public String getInformationId() {
        return informationId;
    }

    public void setInformationId(String informationId) {
        this.informationId = informationId;
    }

    public String getInformationTopic() {
        return informationTopic;
    }

    public void setInformationTopic(String informationTopic) {
        this.informationTopic = informationTopic;
    }

    public String getInformationAuthor() {
        return informationAuthor;
    }

    public void setInformationAuthor(String informationAuthor) {
        this.informationAuthor = informationAuthor;
    }

    public String getInformationContent() {
        return informationContent;
    }

    public void setInformationContent(String informationContent) {
        this.informationContent = informationContent;
    }

    public Integer getInformationFabulous() {
        return informationFabulous;
    }

    public void setInformationFabulous(Integer informationFabulous) {
        this.informationFabulous = informationFabulous;
    }

    public Date getInformationReltime() {
        return informationReltime;
    }

    public void setInformationReltime(Date informationReltime) {
        this.informationReltime = informationReltime;
    }

    public Integer getInformationStatus() {
        return informationStatus;
    }

    public void setInformationStatus(Integer informationStatus) {
        this.informationStatus = informationStatus;
    }
}