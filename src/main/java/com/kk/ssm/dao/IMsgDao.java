package com.kk.ssm.dao;

import com.kk.ssm.entity.Message;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;

public interface IMsgDao {
    void addMsg(@Param("fromId") int fromId, @Param("fromName") String fromName, @Param("toId") int toId, @Param("msgText") String msgText, @Param("msgTime") String msgTime);
    Message findByUserAndTime(@Param("toId") int toId, @Param("msgTime") String msgTime);
}
