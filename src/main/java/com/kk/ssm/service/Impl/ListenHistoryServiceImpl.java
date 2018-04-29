package com.kk.ssm.service.Impl;

import com.kk.ssm.dao.IListenHistoryDao;
import com.kk.ssm.entity.ListenHistory;
import com.kk.ssm.service.IListenHistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ListenHistoryServiceImpl implements IListenHistoryService{

    @Resource
    private IListenHistoryDao historyDao;


    @Override
    public List<ListenHistory> getListenHistory(int userNumber) {
        return this.historyDao.getListenHistory(userNumber);
    }

    @Override
    public ListenHistory findLH(int songId, int userNumber) {
        return this.historyDao.findLH(songId, userNumber);
    }

    @Override
    public String updateLT(int songId, int userNumber) {
        historyDao.updateLT(songId, userNumber);
        return "success";
    }

    @Override
    public String insertLH(int songId, int userNumber) {
        historyDao.insertLH(songId, userNumber);
        return "success";
    }
}
