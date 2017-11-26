package com.fcsservice.service;

import com.fcsservice.dao.DictcateDao;
import com.fcsservice.dao.DictdataDao;
import com.fcsservice.model.pojo.Dictcate;
import com.fcsservice.model.pojo.Dictdata;
import com.fcsservice.utils.FcsserviceUtil;
import com.fcsservice.utils.Node3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.Style;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YE on 2017/10/26 15:10.
 */

@Service
@Transactional
public class DictdataService {
    @Autowired
    DictdataDao dictdataDao;
    @Autowired
    DictcateDao dictcateDao;

    public List<Dictdata> getByDatecateId(int datecateId){
        return dictdataDao.getByDatecateId(datecateId);
    }

    public List<Node3> getStyleData(){
        List<Node3> node3s = new ArrayList<Node3>();
        Dictcate dictcate = dictcateDao.getByValue(FcsserviceUtil.STYLE_TAG);
        if (dictcate != null){
            int dictcateId = dictcate.getDictionarycategoryId();
            List<Dictdata> styleList = dictdataDao.getByDatecateId(dictcateId);
            for (int i = 0; i < styleList.size(); i++) {
                Node3 node3 = new Node3();
                node3.setText(styleList.get(i).getDictionarydataValue());
                node3.setValue(styleList.get(i).getDactionarydataId()+"");
                node3s.add(node3);
            }
        }
        if (node3s.size() != 0){
            return node3s;
        }else {
            return null;
        }
    }

    public List<Node3> getModelData(){
        List<Node3> node3s = new ArrayList<Node3>();
        Dictcate dictcate = dictcateDao.getByValue(FcsserviceUtil.MODEL_TAG);
        if (dictcate != null){
            int dictcateId = dictcate.getDictionarycategoryId();
            List<Dictdata> styleList = dictdataDao.getByDatecateId(dictcateId);
            for (int i = 0; i < styleList.size(); i++) {
                Node3 node3 = new Node3();
                node3.setText(styleList.get(i).getDictionarydataValue());
                node3.setValue(styleList.get(i).getDactionarydataId()+"");
                node3s.add(node3);
            }
        }
        if (node3s.size() != 0){
            return node3s;
        }else {
            return null;
        }
    }
}
