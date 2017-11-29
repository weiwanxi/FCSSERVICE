package com.fcsservice.model.pojo;

import java.io.Serializable;
import java.util.Date;

public class Purchase implements Serializable {
    private String purchaseId;

    private String buyerId;

    private String workId;

    private String workName;

    private String sellerId;

    private String sellerName;

    private String purchaseRequest;

    private String purchaseName;

    private String purchasePhone;

    private String purchaseMail;

    private Date purchaseTime;

    private Integer readStatus;

    private Integer purchaseStatus;

    private static final long serialVersionUID = 1L;

    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getPurchaseRequest() {
        return purchaseRequest;
    }

    public void setPurchaseRequest(String purchaseRequest) {
        this.purchaseRequest = purchaseRequest;
    }

    public String getPurchaseName() {
        return purchaseName;
    }

    public void setPurchaseName(String purchaseName) {
        this.purchaseName = purchaseName;
    }

    public String getPurchasePhone() {
        return purchasePhone;
    }

    public void setPurchasePhone(String purchasePhone) {
        this.purchasePhone = purchasePhone;
    }

    public String getPurchaseMail() {
        return purchaseMail;
    }

    public void setPurchaseMail(String purchaseMail) {
        this.purchaseMail = purchaseMail;
    }

    public Date getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(Date purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public Integer getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(Integer readStatus) {
        this.readStatus = readStatus;
    }

    public Integer getPurchaseStatus() {
        return purchaseStatus;
    }

    public void setPurchaseStatus(Integer purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }
}