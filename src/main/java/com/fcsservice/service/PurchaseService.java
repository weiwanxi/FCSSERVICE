package com.fcsservice.service;

import com.fcsservice.dao.AccountDao;
import com.fcsservice.dao.PurchaseDao;
import com.fcsservice.dao.WorkDao;
import com.fcsservice.model.pojo.Purchase;
import com.fcsservice.model.pojo.UserAccount;
import com.fcsservice.model.pojo.Work;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by YE on 2017/11/27 21:46.
 */

@Service
@Transactional
public class PurchaseService {
    @Autowired
    PurchaseDao purchaseDao;
    @Autowired
    AccountDao accountDao;
    @Autowired
    WorkDao workDao;


    private static Logger logger = Logger.getLogger(PurchaseService.class);

    public String addPurchase(String userId,String workId,String designerId,String purchaseRequest,
                               String purchaseName,String purchasePhone,String purchaseMail){

        Purchase oldPurchase = purchaseDao.getPurchaseByUW(userId,workId);
        if (oldPurchase == null) {
            UserAccount buyerAccount = accountDao.getUserAccountById(userId);
            UserAccount sellerAccount = accountDao.getUserAccountById(designerId);
            Work work = workDao.getWorkById(workId);
            if (buyerAccount!=null && sellerAccount!=null && work!=null){
                Purchase purchase = new Purchase();
                String purchaseId = UUID.randomUUID().toString().replaceAll("-", "");
                purchase.setPurchaseId(purchaseId);
                purchase.setBuyerId(userId);
                purchase.setWorkId(workId);
                purchase.setWorkName(work.getWorkName());
                purchase.setSellerId(designerId);
                purchase.setSellerName(sellerAccount.getUserAccount());
                purchase.setPurchaseRequest(purchaseRequest);
                purchase.setPurchaseName(purchaseName);
                purchase.setPurchasePhone(purchasePhone);
                purchase.setPurchaseMail(purchaseMail);
                purchase.setPurchaseTime(new Date());
                purchase.setPurchaseStatus(-1); //设置为未删除
                purchase.setReadStatus(0);//设置为未查看
                purchaseDao.addPurchase(purchase);
            }else{
                return "发送购买请求失败，信息错误";
            }
        }else {
            return "已发送过购买请求，不能重复发送";
        }
        return null;
    }

    public Map<String,String[]> getUserBuy(String userId){
        Map<String,String[]> map = new HashMap<String, String[]>();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        List<Purchase> purchaseList = purchaseDao.getUserBuy(userId);
        if (purchaseList == null){
            return null;
        }

        int size = purchaseList.size();
        String[] id = new String[size];
        String[] image = new String[size];
        String[] title = new String[size];
        String[] designer = new String[size];
        String[] time = new String[size];

        for (int i = 0; i < size; i++) {
            Purchase purchase = purchaseList.get(i);
            id[i] = purchase.getPurchaseId();
            title[i] = purchase.getWorkName();
            designer[i] = purchase.getSellerName();
            time[i] = format.format(purchase.getPurchaseTime());

            Work work = workDao.getWorkById(purchase.getWorkId());
            UserAccount account = accountDao.getUserAccountById(purchase.getSellerId());
            if (work != null && account != null){
                image[i] = work.getWorkPicture1();
            }else{
                image[i] = "";
                logger.info("购买记录： "+purchase.getPurchaseId()+" 已失效");
            }
        }

        map.put("id",id);
        map.put("image",image);
        map.put("title",title);
        map.put("designer",designer);
        map.put("time",time);
        return map;
    }

    public Map<String,String[]> getUserSell(String userId) {
        Map<String, String[]> map = new HashMap<String, String[]>();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Purchase> purchaseList = purchaseDao.getUserSell(userId);
        if (purchaseList == null){
            return null;
        }
        int size = purchaseList.size();
        String[] id = new String[size];
        String[] title = new String[size];
        String[] buyer = new String[size];
        String[] time = new String[size];
        String[] status = new String[size];

        for (int i = 0; i < size; i++) {
            Purchase purchase = purchaseList.get(i);
            id[i] = purchase.getPurchaseId();
            title[i] = purchase.getWorkName();
            buyer[i] = purchase.getPurchaseName();
            time[i] = format.format(purchase.getPurchaseTime());
            status[i] = purchase.getReadStatus()+"";
        }

        map.put("id",id);
        map.put("title",title);
        map.put("buyer",buyer);
        map.put("time",time);
        map.put("status",status);
        return map;
    }

    public boolean setReaded(String purchaseId){
        Purchase purchase = purchaseDao.getPurchase(purchaseId);
        if (purchase != null && purchase.getReadStatus() != 1){
            purchase.setReadStatus(1);
            purchaseDao.updatePurchase(purchase);
            return true;
        }else {
            return false;
        }
    }

    public int getUnRead(String userId){
        return purchaseDao.getUserUnRead(userId);
    }

    public Map<String,String> getPurchaseDetail(String purchaseId){
        Map<String,String> map = new HashMap<String, String>();
        Purchase purchase = purchaseDao.getBuyerPurchaseDetail(purchaseId);

        if (purchase != null){
            //查询作品是否被删除
            Work work = workDao.getWorkById(purchase.getWorkId());
            if (work != null){
                map.put("workId",purchase.getWorkId());
            }else {
                map.put("workId","");
            }

            map.put("designerId",purchase.getSellerId());

            String phone = purchase.getPurchasePhone();
            if (phone.equals("null")){
                phone = "";
            }
            String mail = purchase.getPurchaseMail();
            if (mail.equals("null")){
                mail = "";
            }

            map.put("designerName",purchase.getSellerName());
            map.put("workName",purchase.getWorkName());
            map.put("purchaseRequest",purchase.getPurchaseRequest());
            map.put("buyerName",purchase.getPurchaseName());
            map.put("buyerPhone",phone);
            map.put("buyerMail",mail);
        }else {
            return null;
        }

        return map;
    }

    public Map<String,String> getSellDetail(String sellId){
        Map<String,String> map = new HashMap<String, String>();
        Purchase purchase = purchaseDao.getSellerSellDetail(sellId);

        if (purchase != null){
            Work work = workDao.getWorkById(purchase.getWorkId());
            if (work != null){
                map.put("workId",purchase.getWorkId());
            }else {
                map.put("workId","");
            }

            String phone = purchase.getPurchasePhone();
            if (phone.equals("null")){
                phone = "";
            }
            String mail = purchase.getPurchaseMail();
            if (mail.equals("null")){
                mail = "";
            }

            map.put("workName",purchase.getWorkName());
            map.put("purchaseRequest",purchase.getPurchaseRequest());
            map.put("buyerName",purchase.getPurchaseName());
            map.put("buyerPhone",phone);
            map.put("buyerMail",mail);
        }else {
            return null;
        }

        return map;
    }

    public void deleteAPurchase(String purchaseId){
        Purchase purchase = purchaseDao.getPurchase(purchaseId);
        if (purchase.getPurchaseStatus() == 1){
            purchaseDao.deleteAPurchase(purchase.getPurchaseId());
        }else {
            purchaseDao.deleteABuyerPurchase(purchase.getPurchaseId());
        }
    }

    public void deleteAllPurchase(String userId){
        List<Purchase> purchaseList = purchaseDao.getUserBuy(userId);
        for (int i=0;i<purchaseList.size();i++){
            Purchase purchase = purchaseList.get(i);
            if (purchase.getPurchaseStatus() == 1){
                purchaseDao.deleteAPurchase(purchase.getPurchaseId());
            }else {
                purchaseDao.deleteABuyerPurchase(purchase.getPurchaseId());
            }
        }
    }

    public void deleteASell(String sellId){
        Purchase purchase = purchaseDao.getPurchase(sellId);
        if (purchase.getPurchaseStatus() == 0){
            purchaseDao.deleteAPurchase(purchase.getPurchaseId());
        }else {
            purchaseDao.deleteASellerPurchase(purchase.getPurchaseId());
        }
    }

    public void deleteAllSell(String sellerId){
        List<Purchase> purchaseList = purchaseDao.getUserSell(sellerId);
        for (int i=0;i<purchaseList.size();i++){
            Purchase purchase = purchaseList.get(i);
            if (purchase.getPurchaseStatus() == 0){
                purchaseDao.deleteAPurchase(purchase.getPurchaseId());
            }else {
                purchaseDao.deleteASellerPurchase(purchase.getPurchaseId());
            }
        }
    }
}
