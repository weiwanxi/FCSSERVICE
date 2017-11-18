package com.fcsservice.model.dao;

import com.fcsservice.model.pojo.Album;

import java.util.List;

public interface AlbumMapper {
    int deleteByPrimaryKey(String albumId);

    int insert(Album record);

    int insertSelective(Album record);

    Album selectByPrimaryKey(String albumId);

    List<Album> selectByUserId(String user_id);

    int updateByPrimaryKeySelective(Album record);

    int updateByPrimaryKey(Album record);
}