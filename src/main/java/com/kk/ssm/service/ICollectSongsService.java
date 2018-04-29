package com.kk.ssm.service;

import com.kk.ssm.entity.CollectSongs;
import com.kk.ssm.entity.Song;

import java.util.List;

public interface ICollectSongsService {
    public List<CollectSongs> getCollectSongs(int usernumber);

    public String collectionSong(int songId, int usernumber);

    public CollectSongs findCollectSong(int songId, int usernumber);

    public String deleteCollectSong(int songId, int usernumber);
}
