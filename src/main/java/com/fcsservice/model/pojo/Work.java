package com.fcsservice.model.pojo;

import java.io.Serializable;
import java.util.Date;

public class Work implements Serializable {
    private String workId;

    private String designerId;

    private String workName;

    private Integer typeId;

    private Integer styleId;

    private String albumId;

    private Integer fabricId;

    private Integer modelId;

    private String workColor;

    private String workIntro;

    private String workPicture1;

    private String workPicture2;

    private String workPicture3;

    private String workPicture4;

    private String workPicture5;

    private String workPicture6;

    private Date workReltime;

    private Integer worlFabulous;

    private Integer workStatus;

    private static final long serialVersionUID = 1L;

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public String getDesignerId() {
        return designerId;
    }

    public void setDesignerId(String designerId) {
        this.designerId = designerId;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getStyleId() {
        return styleId;
    }

    public void setStyleId(Integer styleId) {
        this.styleId = styleId;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public Integer getFabricId() {
        return fabricId;
    }

    public void setFabricId(Integer fabricId) {
        this.fabricId = fabricId;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public String getWorkColor() {
        return workColor;
    }

    public void setWorkColor(String workColor) {
        this.workColor = workColor;
    }

    public String getWorkIntro() {
        return workIntro;
    }

    public void setWorkIntro(String workIntro) {
        this.workIntro = workIntro;
    }

    public String getWorkPicture1() {
        return workPicture1;
    }

    public void setWorkPicture1(String workPicture1) {
        this.workPicture1 = workPicture1;
    }

    public String getWorkPicture2() {
        return workPicture2;
    }

    public void setWorkPicture2(String workPicture2) {
        this.workPicture2 = workPicture2;
    }

    public String getWorkPicture3() {
        return workPicture3;
    }

    public void setWorkPicture3(String workPicture3) {
        this.workPicture3 = workPicture3;
    }

    public String getWorkPicture4() {
        return workPicture4;
    }

    public void setWorkPicture4(String workPicture4) {
        this.workPicture4 = workPicture4;
    }

    public String getWorkPicture5() {
        return workPicture5;
    }

    public void setWorkPicture5(String workPicture5) {
        this.workPicture5 = workPicture5;
    }

    public String getWorkPicture6() {
        return workPicture6;
    }

    public void setWorkPicture6(String workPicture6) {
        this.workPicture6 = workPicture6;
    }

    public Date getWorkReltime() {
        return workReltime;
    }

    public void setWorkReltime(Date workReltime) {
        this.workReltime = workReltime;
    }

    public Integer getWorlFabulous() {
        return worlFabulous;
    }

    public void setWorlFabulous(Integer worlFabulous) {
        this.worlFabulous = worlFabulous;
    }

    public Integer getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(Integer workStatus) {
        this.workStatus = workStatus;
    }
}