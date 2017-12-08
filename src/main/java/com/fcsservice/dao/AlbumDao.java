package com.fcsservice.dao;

import com.fcsservice.model.dto.AlbumMapper;
import com.fcsservice.model.pojo.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by YE on 2017/11/18 19:57.
 */

@Repository
public class AlbumDao {
    @Autowired
    AlbumMapper albumMapper;

    public List<Album> getUserAlbum(String userId){
        return albumMapper.selectByUserId(userId);
    }

    public Album getALbumById(String albumId){
        return albumMapper.selectByPrimaryKey(albumId);
    }

    public void addAlbum(Album album){
        albumMapper.insertSelective(album);
    }

    public void updateAlbum(Album album){
        albumMapper.updateByPrimaryKeySelective(album);
    }

    public void deleteAlbum(String albumId){
        albumMapper.deleteByPrimaryKey(albumId);
    }

    public Album getAlbumByName(String userId,String albumName){
        return albumMapper.selectByName(userId,albumName);
    }
}
