package com.kk.ssm.service.Impl;

import com.kk.ssm.dao.ICollectSongsDao;
import com.kk.ssm.entity.CollectSongs;
import com.kk.ssm.service.ICollectSongsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CollectSongsServiceImpl implements ICollectSongsService{

    @Resource
    private ICollectSongsDao iCollectSongsDao;

    @Override
    public List<CollectSongs> getCollectSongs(int usernumber) {
        return this.iCollectSongsDao.getCollectSongs(usernumber);
    }

    @Override
    public String collectionSong(int songId, int usernumber) {
        this.iCollectSongsDao.collectionSong(songId, usernumber);
        return "success";
    }

    @Override
    public CollectSongs findCollectSong(int songId, int usernumber) {
        return this.iCollectSongsDao.findCollectSong(songId,usernumber);
    }

    @Override
    public String deleteCollectSong(int songId, int usernumber) {
        this.iCollectSongsDao.deleteCollectSong(songId,usernumber);
        return "success";
    }

    public ICollectSongsDao getiCollectSongsDao() {
        return iCollectSongsDao;
    }

    public void setiCollectSongsDao(ICollectSongsDao iCollectSongsDao) {
        this.iCollectSongsDao = iCollectSongsDao;
    }
}
