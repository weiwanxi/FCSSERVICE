package com.fcsservice.model;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by YE on 2017/10/18.
 */
@Mapper
@Repository
public interface AccountMapper {
    String getPassword(String user_account);
    //String existUseraccount(String user_account);
    //void addAccount(Account account);
    //void updatePassword(String user_account,String user_password);
}
