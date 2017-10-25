package com.fcsservice.model.dao;

import com.fcsservice.model.pojo.Purchase;

public interface PurchaseMapper {
    int deleteByPrimaryKey(String purchaseId);

    int insert(Purchase record);

    int insertSelective(Purchase record);

    Purchase selectByPrimaryKey(String purchaseId);

    int updateByPrimaryKeySelective(Purchase record);

    int updateByPrimaryKey(Purchase record);
}