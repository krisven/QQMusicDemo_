package com.kk.ssm.service;

import com.kk.ssm.entity.Song;

import java.util.List;

public interface ISongService {
    public List<Song> getAllSong();

    public Song findSongBySongId(int songId);
}
