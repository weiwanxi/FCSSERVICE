package com.fcsservice.model.pojo;

import java.io.Serializable;

public class Album implements Serializable {
    private String albumId;

    private String userId;

    private String albumName;

    private String albumPicture;

    private String albumTime;

    private static final long serialVersionUID = 1L;

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumPicture() {
        return albumPicture;
    }

    public void setAlbumPicture(String albumPicture) {
        this.albumPicture = albumPicture;
    }

    public String getAlbumTime() {
        return albumTime;
    }

    public void setAlbumTime(String albumTime) {
        this.albumTime = albumTime;
    }
}