package com.fcsservice.service;

import com.fcsservice.dao.ComponentDao;
import com.fcsservice.model.pojo.Component;
import com.fcsservice.model.pojo.CostumeType;
import com.fcsservice.utils.Node2;
import com.fcsservice.utils.Node3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YE on 2017/11/27 0:18.
 */

@Service
@Transactional
public class ComponentService {
    @Autowired
    ComponentDao componentDao;

    public List<Node2> getComponentData(){
        List<Component> level1 = componentDao.getComentListByLevel(1);
        List<Node2> node2s = new ArrayList<Node2>();
        for (int i=0;i<level1.size();i++){
            List<Component> level2 = componentDao.getComentListByPId(level1.get(i).getComponentId());
            List<Node3> node3s = new ArrayList<Node3>();
            for (int j=0;j<level2.size();j++){
                Node3 node3 = new Node3();
                node3.setValue(level2.get(j).getComponentId()+"");
                node3.setText(level2.get(j).getComponentName());
                node3s.add(node3);
            }
            Node2 node2 = new Node2();
            node2.setChildren(node3s);
            node2.setValue(level1.get(i).getComponentId()+"");
            node2.setText(level1.get(i).getComponentName());
            node2s.add(node2);
        }

        return node2s;
    }

    public List<Node2> getScreenComponentData(){
        List<Component> level1 = componentDao.getComentListByLevel(1);
        List<Node2> node2s = new ArrayList<Node2>();

        //每一层额外加一栏，用于查找所有
        Node2 nodee2 = new Node2();
        Node3 nodee3 = new Node3();
        List<Node3> node3ss = new ArrayList<Node3>();
        nodee3.setValue("-1");
        nodee3.setText("全部");
        node3ss.add(nodee3);
        nodee2.setValue("-1");
        nodee2.setText("全部");
        nodee2.setChildren(node3ss);
        node2s.add(nodee2);

        for (int i=0;i<level1.size();i++){
            List<Component> level2 = componentDao.getComentListByPId(level1.get(i).getComponentId());
            List<Node3> node3s = new ArrayList<Node3>();
            for (int j=0;j<level2.size();j++){
                Node3 node3 = new Node3();
                node3.setValue(level2.get(j).getComponentId()+"");
                node3.setText(level2.get(j).getComponentName());
                node3s.add(node3);
            }
            Node2 node2 = new Node2();
            node2.setChildren(node3s);
            node2.setValue(level1.get(i).getComponentId()+"");
            node2.setText(level1.get(i).getComponentName());
            node2s.add(node2);
        }

        return node2s;
    }
}
