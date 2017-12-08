package com.fcsservice.model.dto;

import com.fcsservice.model.pojo.Album;

import java.util.List;

public interface AlbumMapper {
    int deleteByPrimaryKey(String albumId);

    int insert(Album record);

    int insertSelective(Album record);

    Album selectByPrimaryKey(String albumId);

    List<Album> selectByUserId(String user_id);

    Album selectByName(String userId,String albumName);

    int updateByPrimaryKeySelective(Album record);

    int updateByPrimaryKey(Album record);
}