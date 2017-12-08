package com.fcsservice.service;

import com.fcsservice.dao.*;
import com.fcsservice.model.pojo.*;
import com.fcsservice.utils.FcsserviceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

/**
 * Created by YE on 2017/10/18.
 */

@Service
@Transactional
public class AccountService {
    @Autowired
    private AccountDao accountDao;
    @Autowired
    UserDataDao userDataDao;
    @Autowired
    DictdataDao dictdataDao;
    @Autowired
    TagDao tagDao;
    @Autowired
    AlbumDao albumDao;

    public String login(String user_account,String user_password){
        UserAccount userAccount = accountDao.getUserAccountByAccount(user_account);
        if (userAccount == null){
            UserData userData = userDataDao.getDataByMail(user_account);
            if (userData != null){
                userAccount = accountDao.getUserAccountById(userData.getUserId());
            }
        }

        if (userAccount == null){
            return  null;
        }else if (userAccount.getUserPassword().equals(user_password)) {
            return userAccount.getUserId();
        }else {
            return null;
        }
    }

    public boolean existUserAccount(String user_account){
        UserAccount userAccount = accountDao.getUserAccountByAccount(user_account);
        return userAccount==null;
    }

    public boolean addUserAccount(String user_account,String user_password,String mail,int user_type,String tag){
        UserAccount userAccount = new UserAccount();
        if (user_account!=null && user_password!=null && mail!=null && tag!=null){

            //设计师账号
            String userId = UUID.randomUUID().toString().replaceAll("-", "");
            userAccount.setUserId(userId);
            userAccount.setUserAccount(user_account);
            userAccount.setUserPassword(user_password);
            userAccount.setUserType(user_type);
            userAccount.setUserRegtime(new Date());
            userAccount.setUserStatus(0);
            accountDao.addUserAccount(userAccount);

            //设计师普通资料
            String data_id = UUID.randomUUID().toString().replaceAll("-", "");
            UserData userData = new UserData();
            userData.setDataId(data_id);
            userData.setUserId(userId);
            userData.setDataMail(mail);
            userDataDao.addUserData(userData);

            //设计师设计标签
            if (user_type == FcsserviceUtil.DESIGNER){
                tag = tag.substring(1,tag.length());
                String tagStrArray[] = tag.split(";");
                for (int i=0;i<tagStrArray.length;i++){
                    Dictdata dictdata = dictdataDao.getDictDataById(Integer.parseInt(tagStrArray[i]));
                    if (dictdata != null){
                        Tag tagg = new Tag();
                        tagg.setUserId(userId);
                        tagg.setTagId(dictdata.getDactionarydataId());
                        tagDao.addTag(tagg);
                    }
                }

                //创建默认专辑
                Album album = new Album();
                String albumId = UUID.randomUUID().toString().replaceAll("-", "");
                album.setAlbumId(albumId);
                album.setAlbumName("默认专辑");
                album.setUserId(userId);
                album.setAlbumPicture("null");
                album.setAlbumTime(new Date());
                albumDao.addAlbum(album);
            }
            return true;

        }
        else{
            return false;
        }
    }

    public void updatePassword(String userId,String password){
        UserAccount userAccount = new UserAccount();
        userAccount.setUserId(userId);
        userAccount.setUserPassword(password);

        accountDao.updatePassword(userAccount);
    }

    public UserAccount getAccountById(String userId){
        return accountDao.getUserAccountById(userId);
    }
}
