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
    public void updateWork(Work work){
        workMapper.updateByPrimaryKeySelective(work);
    }

    public void addWork(Work work){
        workMapper.insertSelective(work);
    }

    public void deleteWork(String workId){
        workMapper.deleteByPrimaryKey(workId);
    }

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
        return workMapper.selectWorkNumberByAlbumId(albumId);
    }

    public List<Work> getWorkOrderByComment(int page, int number,int screen){
        return workMapper.selectOrderByComment(page,number,screen);
    }

    public List<Work> getWorkOrderByFabulous(int page, int number,int screen){
        return workMapper.selectOrderByFabulous(page,number,screen);
    }

    public List<Work> getWorkListByAlbumId(String albumId){
        return workMapper.selectWorkListByAlbumId(albumId);
    }

    public List<Work> getWorkBySearch(String searchText,int page,int number){
        return workMapper.selectWorkBySearch(searchText,page,number);
    }

    public void deleteByAlbumId(String albumId){
        workMapper.deleteByAlbumId(albumId);
    }

    public Work getWorkById(String workId){
        return workMapper.selectByPrimaryKey(workId);
    }
}
