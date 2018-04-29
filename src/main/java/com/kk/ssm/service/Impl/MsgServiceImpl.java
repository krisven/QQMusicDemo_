package com.kk.ssm.service.Impl;

import com.kk.ssm.dao.IMsgDao;
import com.kk.ssm.entity.Message;
import com.kk.ssm.service.IMsgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;

@Service
public class MsgServiceImpl implements IMsgService{

    @Resource
    private IMsgDao msgDao;

    @Override
    public String addMsg(int fromId, String fromName, int toId, String msgText, String msgTime) {
        msgDao.addMsg(fromId, fromName, toId, msgText, msgTime);
        return "success";
    }

    @Override
    public Message findByUserAndTime(int toId, String msgTime) {
        return msgDao.findByUserAndTime(toId, msgTime);
    }
}
