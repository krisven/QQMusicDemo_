package com.kk.ssm.dao;

import com.kk.ssm.entity.CollectSongs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ICollectSongsDao {
    List<CollectSongs> getCollectSongs(int usernumber);

    int collectionSong(@Param("songId") int songId, @Param("usernumber") int usernumber);

    CollectSongs findCollectSong(@Param("songId") int songId, @Param("usernumber") int usernumber);

    int deleteCollectSong(@Param("songId") int songId,@Param("usernumber") int usernumber);
}
