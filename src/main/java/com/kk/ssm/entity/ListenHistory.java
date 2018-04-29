package com.kk.ssm.entity;

public class ListenHistory {
    private int songId;
    private String songName;
    private String singer;
    private String album;
    private int userNumber;
    private int listenTime;

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getAlbum() {
        return album;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    public int getListenTime() {
        return listenTime;
    }

    public void setListenTime(int listenTime) {
        this.listenTime = listenTime;
    }
}
