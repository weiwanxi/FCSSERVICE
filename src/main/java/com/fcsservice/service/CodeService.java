package com.fcsservice.service;

import com.fcsservice.dao.CodeDao;
import com.fcsservice.model.pojo.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by YE on 2017/10/27 19:11.
 */

@Service
@Transactional
public class CodeService {
    @Autowired
    CodeDao codeDao;

    public Code sentMail(String mail,int type){
        Code code = codeDao.sentMail(mail,type);
        return code;
    }

    public void saveCode(String mail,String codee,int type){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Code code = new Code();
        code.setCode(codee);
        code.setMail(mail);
        code.setType(type);
        code.setTime(simpleDateFormat.format(new Date()));
        codeDao.saveCode(code);

        //60秒后删除验证码
        DeleteThread deleteThread = new DeleteThread(mail,type);
        Thread thread = new Thread(deleteThread);
        thread.start();
    }

    class DeleteThread implements Runnable{
        String mail;
        int type;

        public DeleteThread(String mail,int type){
            this.mail = mail;
            this.type = type;
        }

        public void run() {
            try {
                Thread.sleep(60000);
                codeDao.deleteCode(mail,type);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
