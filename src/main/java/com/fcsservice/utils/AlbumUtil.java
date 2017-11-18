package com.fcsservice.utils;

import com.fcsservice.model.pojo.Album;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YE on 2017/11/18 19:59.
 */
public class AlbumUtil {

    public Map<String,String[]> getAlbum(List<Album> albumList){
        Map<String,String[]> map = new HashMap<String, String[]>();
        String[] albumId = new String[albumList.size()];
        String[] albumPortrait = new String[albumList.size()];
        String[] albumName = new String[albumList.size()];

        for (int i=0;i<albumList.size();i++){
            albumId[i] = albumList.get(i).getAlbumId();
            albumPortrait[i] = albumList.get(i).getAlbumPicture();
            albumName[i] = albumList.get(i).getAlbumName();
        }

        map.put("albumId",albumId);
        map.put("albumPortrait",albumPortrait);
        map.put("albumName",albumName);
        return map;
    }
}
