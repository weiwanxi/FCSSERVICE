package com.fcsservice.model.dao;

import com.fcsservice.model.pojo.Album;

public interface AlbumMapper {
    int deleteByPrimaryKey(String albumId);

    int insert(Album record);

    int insertSelective(Album record);

    Album selectByPrimaryKey(String albumId);

    int updateByPrimaryKeySelective(Album record);

    int updateByPrimaryKey(Album record);
}