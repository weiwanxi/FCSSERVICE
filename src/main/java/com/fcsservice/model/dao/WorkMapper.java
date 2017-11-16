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

    int selectByDesignerId(String designerId);

    int updateByPrimaryKeySelective(Work record);

    int updateByPrimaryKey(Work record);
}