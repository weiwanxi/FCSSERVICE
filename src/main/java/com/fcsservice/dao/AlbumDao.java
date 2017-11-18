package com.fcsservice.dao;

import com.fcsservice.model.dao.AlbumMapper;
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
}
