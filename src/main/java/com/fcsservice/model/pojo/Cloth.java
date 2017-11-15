package com.fcsservice.model.pojo;

import java.io.Serializable;
import java.util.Date;

public class Cloth implements Serializable {
    private String clothId;

    private String clothName;

    private String clothSupplier;

    private Integer clothPrice;

    private String clothNo;

    private Integer clothMoq;

    private Integer componentId;

    private Integer purposeId;

    private Integer weaveId;

    private Integer makeId;

    private String clothPicture1;

    private String clothPicture2;

    private String clothPicture3;

    private String clothPicture4;

    private Integer clothElastic;

    private String clothTcx;

    private String clothTpx;

    private Integer clothFabulous;

    private Date clothReltime;

    private Integer clothStatus;

    private static final long serialVersionUID = 1L;

    public String getClothId() {
        return clothId;
    }

    public void setClothId(String clothId) {
        this.clothId = clothId;
    }

    public String getClothName() {
        return clothName;
    }

    public void setClothName(String clothName) {
        this.clothName = clothName;
    }

    public String getClothSupplier() {
        return clothSupplier;
    }

    public void setClothSupplier(String clothSupplier) {
        this.clothSupplier = clothSupplier;
    }

    public Integer getClothPrice() {
        return clothPrice;
    }

    public void setClothPrice(Integer clothPrice) {
        this.clothPrice = clothPrice;
    }

    public String getClothNo() {
        return clothNo;
    }

    public void setClothNo(String clothNo) {
        this.clothNo = clothNo;
    }

    public Integer getClothMoq() {
        return clothMoq;
    }

    public void setClothMoq(Integer clothMoq) {
        this.clothMoq = clothMoq;
    }

    public Integer getComponentId() {
        return componentId;
    }

    public void setComponentId(Integer componentId) {
        this.componentId = componentId;
    }

    public Integer getPurposeId() {
        return purposeId;
    }

    public void setPurposeId(Integer purposeId) {
        this.purposeId = purposeId;
    }

    public Integer getWeaveId() {
        return weaveId;
    }

    public void setWeaveId(Integer weaveId) {
        this.weaveId = weaveId;
    }

    public Integer getMakeId() {
        return makeId;
    }

    public void setMakeId(Integer makeId) {
        this.makeId = makeId;
    }

    public String getClothPicture1() {
        return clothPicture1;
    }

    public void setClothPicture1(String clothPicture1) {
        this.clothPicture1 = clothPicture1;
    }

    public String getClothPicture2() {
        return clothPicture2;
    }

    public void setClothPicture2(String clothPicture2) {
        this.clothPicture2 = clothPicture2;
    }

    public String getClothPicture3() {
        return clothPicture3;
    }

    public void setClothPicture3(String clothPicture3) {
        this.clothPicture3 = clothPicture3;
    }

    public String getClothPicture4() {
        return clothPicture4;
    }

    public void setClothPicture4(String clothPicture4) {
        this.clothPicture4 = clothPicture4;
    }

    public Integer getClothElastic() {
        return clothElastic;
    }

    public void setClothElastic(Integer clothElastic) {
        this.clothElastic = clothElastic;
    }

    public String getClothTcx() {
        return clothTcx;
    }

    public void setClothTcx(String clothTcx) {
        this.clothTcx = clothTcx;
    }

    public String getClothTpx() {
        return clothTpx;
    }

    public void setClothTpx(String clothTpx) {
        this.clothTpx = clothTpx;
    }

    public Integer getClothFabulous() {
        return clothFabulous;
    }

    public void setClothFabulous(Integer clothFabulous) {
        this.clothFabulous = clothFabulous;
    }

    public Date getClothReltime() {
        return clothReltime;
    }

    public void setClothReltime(Date clothReltime) {
        this.clothReltime = clothReltime;
    }

    public Integer getClothStatus() {
        return clothStatus;
    }

    public void setClothStatus(Integer clothStatus) {
        this.clothStatus = clothStatus;
    }
}