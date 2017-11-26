package com.fcsservice.service;

import com.fcsservice.dao.CostumeTypeDao;
import com.fcsservice.model.pojo.CostumeType;
import com.fcsservice.utils.Node1;
import com.fcsservice.utils.Node2;
import com.fcsservice.utils.Node3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YE on 2017/11/26 21:20.
 */

@Service
@Transactional
public class CostumeTypeService {
    @Autowired
    CostumeTypeDao typeDao;

    public List<Node1> getCostumeTypeData(){
        List<CostumeType> level1 = typeDao.getCostumeListByLevel(1);
        List<Node1> node1s = new ArrayList<Node1>();
        for (int i=0;i<level1.size();i++){
            List<CostumeType> level2 = typeDao.getCostumeListByPId(level1.get(i).getTypeId());
            List<Node2> node2s = new ArrayList<Node2>();
            for (int j=0;j<level2.size();j++){
                List<CostumeType> level3 = typeDao.getCostumeListByPId(level2.get(j).getTypeId());
                Node2 node2 = new Node2();
                node2.setChildren(getList(level3));
                node2.setValue(level2.get(j).getTypeId()+"");
                node2.setText(level2.get(j).getTypeName());
                node2s.add(node2);
            }
            Node1 node1 = new Node1();
            node1.setChildren(node2s);
            node1.setValue(level1.get(i).getTypeId()+"");
            node1.setText(level1.get(i).getTypeName());
            node1s.add(node1);
        }

        return node1s;
    }

    private List<Node3> getList(List<CostumeType> costumeTypeList){
        List<Node3> list = new ArrayList<Node3>();

        for (int i = 0; i < costumeTypeList.size(); i++) {
            Node3 node3 = new Node3();
            node3.setValue(costumeTypeList.get(i).getTypeId()+"");
            node3.setText(costumeTypeList.get(i).getTypeName());
            list.add(node3);
        }

        return list;
    }
}
