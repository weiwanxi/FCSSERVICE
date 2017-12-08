package com.fcsservice.dao;

import com.fcsservice.model.dto.CodeMapper;
import com.fcsservice.model.pojo.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by YE on 2017/10/27 19:11.
 */

@Repository
public class CodeDao {
    @Autowired
    CodeMapper codeMapper;

    public Code sentMail(String mail,int type){
        return codeMapper.selectByMail(mail,type);
    }

    public void saveCode(Code code){
        codeMapper.insert(code);
    }

    public void deleteCode(String mail,int type){
        codeMapper.deleteByMail(mail,type);
    }
}
