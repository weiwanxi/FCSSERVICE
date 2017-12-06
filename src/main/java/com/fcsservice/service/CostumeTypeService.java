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
                List<Node3> node3s = new ArrayList<Node3>();
                for (int k = 0; k < level3.size(); k++) {
                    Node3 node3 = new Node3();
                    node3.setValue(level3.get(k).getTypeId()+"");
                    node3.setText(level3.get(k).getTypeName());
                    node3s.add(node3);
                }
                Node2 node2 = new Node2();
                node2.setChildren(node3s);
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

    public List<Node1> getScreenCostumeTypeData(){
        List<CostumeType> level1 = typeDao.getCostumeListByLevel(1);
        List<Node1> node1s = new ArrayList<Node1>();
        //每一层额外加一栏，用于查找所有
        Node1 nodee1 = new Node1();
        Node2 nodee2 = new Node2();
        Node3 nodee3 = new Node3();
        List<Node2> node2ss = new ArrayList<Node2>();
        List<Node3> node3ss = new ArrayList<Node3>();
        nodee3.setValue("-1");
        nodee3.setText("全部");
        node3ss.add(nodee3);
        nodee2.setValue("-1");
        nodee2.setText("全部");
        nodee2.setChildren(node3ss);
        node2ss.add(nodee2);
        nodee1.setValue("-1");
        nodee1.setText("全部");
        nodee1.setChildren(node2ss);
        node1s.add(nodee1);

        for (int i=0;i<level1.size();i++){
            List<CostumeType> level2 = typeDao.getCostumeListByPId(level1.get(i).getTypeId());
            List<Node2> node2s = new ArrayList<Node2>();
            for (int j=0;j<level2.size();j++){
                List<CostumeType> level3 = typeDao.getCostumeListByPId(level2.get(j).getTypeId());
                List<Node3> node3s = new ArrayList<Node3>();
                for (int k = 0; k < level3.size(); k++) {
                    Node3 node3 = new Node3();
                    node3.setValue(level3.get(k).getTypeId()+"");
                    node3.setText(level3.get(k).getTypeName());
                    node3s.add(node3);
                }
                Node2 node2 = new Node2();
                node2.setChildren(node3s);
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
}
