package com.kk.ssm.service.Impl;

import com.kk.ssm.dao.ISongDao;
import com.kk.ssm.entity.Song;
import com.kk.ssm.service.ISongService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SongServiceImpl implements ISongService {

    @Resource
    private ISongDao songDao;

    @Override
    public List<Song> getAllSong() {
        return this.songDao.getAllSongs();
    }

    @Override
    public Song findSongBySongId(int songId) {
        return this.songDao.findSongBySongId(songId);
    }

    public ISongDao getSongDao() {
        return songDao;
    }

    public void setSongDao(ISongDao songDao) {
        this.songDao = songDao;
    }
}
