package com.fcsservice.model.dao;

import com.fcsservice.form.DesignerForm;
import com.fcsservice.model.pojo.Work;

import java.util.List;

public interface WorkMapper {
    int deleteByPrimaryKey(String workId);

    int insert(Work record);

    int insertSelective(Work record);

    Work selectByPrimaryKey(String workId);

    List<DesignerForm> selectDesignerWorkNumber();

    List<DesignerForm> selectDesignerWorkNumberByNumber(int number);

    List<Work> selectOrderByComment(int page, int number);

    List<Work> selectOrderByFabulous(int page, int number);

    List<Work> selectWorkListByAlbumId(String albumId);

    int deleteByAlbumId(String albumId);

    int selectByDesignerId(String designerId);

    int selectWorkNumberByAlbumId(String albumId);

    int updateByPrimaryKeySelective(Work record);

    int updateByPrimaryKey(Work record);
}