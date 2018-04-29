package com.kk.ssm.service;

import com.kk.ssm.entity.ListenHistory;

import java.util.List;

public interface IListenHistoryService {
    public List<ListenHistory> getListenHistory(int userNumber);

    public ListenHistory findLH(int songId, int userNumber);

    public String updateLT(int songId, int userNumber);

    public String insertLH(int songId, int userNumber);
}
