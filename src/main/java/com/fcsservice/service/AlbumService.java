package com.fcsservice.service;

import com.fcsservice.dao.AlbumDao;
import com.fcsservice.dao.WorkDao;
import com.fcsservice.model.pojo.Album;
import com.fcsservice.utils.AlbumUtil;
import com.fcsservice.utils.FcsserviceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by YE on 2017/11/25 15:50.
 */

@Service
@Transactional
public class AlbumService {
    @Autowired
    AlbumDao albumDao;
    @Autowired
    WorkDao workDao;

    FcsserviceUtil fcsserviceUtil = new FcsserviceUtil();

    public Map<String,String[]> getAlbumListByUserId(String userId){
        Map<String,String[]> map = new AlbumUtil().getAlbum(albumDao.getUserAlbum(userId));
        String[] idArray = map.get("albumId");
        String[] numberArray = new String[idArray.length];
        for (int i = 0; i < idArray.length; i++) {
            numberArray[i] = workDao.getWorkNumberByAlbumId(idArray[i])+"";
        }
        map.put("workNumber",numberArray);

        return map;
    }

    public boolean getAlbumByAlbumName(String userId,String albumName){
        return albumDao.getAlbumByName(userId,albumName) != null;
    }

    public Map<String,String> getAlbum(String albumId){
        Map<String,String> map = new HashMap<String, String>();
        Album album = albumDao.getALbumById(albumId);

        if (album != null){
            map.put("albumName",album.getAlbumName());
            map.put("albumImage",album.getAlbumPicture());
            return map;
        }else {
            return null;
        }
    }

    public boolean addAlbum(String userId,String base64Data,String albumName){
        String fileName = fcsserviceUtil.saveImage(fcsserviceUtil.IMAGE1PATH,base64Data);

        if (fileName == null){
            return false;
        }

        Album album = new Album();
        String albumId = UUID.randomUUID().toString().replaceAll("-", "");

        album.setAlbumId(albumId);
        album.setUserId(userId);
        album.setAlbumPicture(fileName);
        album.setAlbumName(albumName);
        album.setAlbumTime(new Date());

        albumDao.addAlbum(album);
        return true;
    }

    public boolean updateAlbum(String userId,String albumId,String base64Data,String albumName){
        try {
            Album album = albumDao.getALbumById(albumId);

            if (album != null){
                //保存新封面
                String fileName = fcsserviceUtil.saveImage(fcsserviceUtil.IMAGE1PATH,base64Data);
                if (fileName == null){
                    return false;
                }

                //删除旧封面
                String oldPortrait = album.getAlbumPicture();
                File deleteFile = new File(fcsserviceUtil.IMAGE1PATH+oldPortrait);
                if (deleteFile.isFile() && deleteFile.exists()) {
                    deleteFile.delete();
                }
                //保存专辑信息
                album.setAlbumId(albumId);
                album.setUserId(userId);
                album.setAlbumName(albumName);
                album.setAlbumPicture(fileName);
                album.setAlbumName(albumName);

                albumDao.updateAlbum(album);
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean deleteAlbum(String albumId){
        Album album = albumDao.getALbumById(albumId);

        if (album != null){
            albumDao.deleteAlbum(albumId);
            return true;
        }else {
            return false;
        }
    }
}
