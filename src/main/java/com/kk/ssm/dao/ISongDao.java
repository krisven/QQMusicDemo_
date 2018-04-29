package com.kk.ssm.dao;

import com.kk.ssm.entity.Song;

import java.util.List;

public interface ISongDao {
    List<Song> getAllSongs();

    Song findSongBySongId(int songId);
}
