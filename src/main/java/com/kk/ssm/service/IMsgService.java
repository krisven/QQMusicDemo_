package com.kk.ssm.service;

import com.kk.ssm.entity.Message;

import java.sql.Timestamp;

public interface IMsgService {
    public String addMsg(int fromId, String fromName, int toId, String msgText, String msgTime);

    public Message findByUserAndTime(int toId, String msgTime);
}
