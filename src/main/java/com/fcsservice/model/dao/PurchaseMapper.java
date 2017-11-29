package com.fcsservice.model.dao;

import com.fcsservice.model.pojo.Purchase;

import java.util.List;

public interface PurchaseMapper {
    int deleteByPrimaryKey(String purchaseId);

    int insert(Purchase record);

    int insertSelective(Purchase record);

    Purchase selectByPrimaryKey(String purchaseId);

    Purchase selectByUW(String userId,String workId);

    Purchase selectBuyerPurchaseDetail(String purchaseId);

    Purchase selectSellerSellDetail(String purchaseId);

    List<Purchase> selectUserBuy(String userId);

    List<Purchase> selectUserSell(String userId);

    int selectSellerUnRead(String sellerId);

    int deleteABuyerPurchase(String purchaseId);

    int deleteASellerPurchase(String purchaseId);

    int updateByPrimaryKeySelective(Purchase record);

    int updateByPrimaryKey(Purchase record);
}