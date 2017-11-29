package com.fcsservice.dao;

import com.fcsservice.model.dao.PurchaseMapper;
import com.fcsservice.model.pojo.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by YE on 2017/11/27 21:47.
 */

@Repository
public class PurchaseDao {
    @Autowired
    PurchaseMapper purchaseMapper;

    public void deleteABuyerPurchase(String purchaseId){
        purchaseMapper.deleteABuyerPurchase(purchaseId);
    }

    public void deleteASellerPurchase(String purchaseId){
        purchaseMapper.deleteASellerPurchase(purchaseId);
    }

    public void deleteAPurchase(String purchaseId){
        purchaseMapper.deleteByPrimaryKey(purchaseId);
    }

    public void addPurchase(Purchase purchase){
        purchaseMapper.insertSelective(purchase);
    }


    public void updatePurchase(Purchase purchase){
        purchaseMapper.updateByPrimaryKeySelective(purchase);
    }

    public Purchase getPurchaseByUW(String userId, String workId){
        return purchaseMapper.selectByUW(userId,workId);
    }


    public Purchase getBuyerPurchaseDetail(String purchaseId){
        return purchaseMapper.selectBuyerPurchaseDetail(purchaseId);
    }

    public Purchase getSellerSellDetail(String sellId){
        return purchaseMapper.selectSellerSellDetail(sellId);
    }

    public List<Purchase> getUserBuy(String userId){
        return purchaseMapper.selectUserBuy(userId);
    }

    public List<Purchase> getUserSell(String userId){
        return purchaseMapper.selectUserSell(userId);
    }

    public Purchase getPurchase(String purchaseId){
        return purchaseMapper.selectByPrimaryKey(purchaseId);
    }

    public int getUserUnRead(String userId){
        return purchaseMapper.selectSellerUnRead(userId);
    }
}
