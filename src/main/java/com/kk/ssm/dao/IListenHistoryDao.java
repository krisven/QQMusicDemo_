package com.kk.ssm.dao;

import com.kk.ssm.entity.ListenHistory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IListenHistoryDao {
    List<ListenHistory> getListenHistory(int userNumber);

    ListenHistory findLH(@Param("songId") int songId, @Param("userNumber") int userNumber);

    void updateLT(@Param("songId") int songId, @Param("userNumber") int userNumber);

    void insertLH(@Param("songId") int songId, @Param("userNumber") int userNumber);
}
