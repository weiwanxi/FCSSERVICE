package com.fcsservice.dao;

import com.fcsservice.form.DesignerForm;
import com.fcsservice.model.dao.WorkMapper;
import com.fcsservice.model.pojo.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by YE on 2017/11/15 10:09.
 */

@Repository
public class WorkDao {
    @Autowired
    WorkMapper workMapper;

    public List<DesignerForm> getDesignerWorkNumber(){
        return workMapper.selectDesignerWorkNumber();
    }

    public List<DesignerForm> getDesignerWorkNumberByNumber(int number){
        return workMapper.selectDesignerWorkNumberByNumber(number);
    }

    public int getWorkNumberByDersignerId(String designerId){
        return workMapper.selectByDesignerId(designerId);
    }

    public int getWorkNumberByAlbumId(String albumId){
        return workMapper.selectByAlbumId(albumId);
    }

    public List<Work> getCostumeOrderByComment(int page, int number){
        return workMapper.selectOrderByComment(page,number);
    }

    public List<Work> getCostumeOrderByFabulous(int page, int number){
        return workMapper.selectOrderByFabulous(page,number);
    }
}
