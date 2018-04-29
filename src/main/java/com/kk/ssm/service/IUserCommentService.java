package com.kk.ssm.service;

import com.kk.ssm.entity.UserComment;

import java.util.List;

public interface IUserCommentService {
    public String addComment(int songId, String commentTime, String commentInf, int userNumber);

    public List<UserComment> findByUser(int userNumber);

    public List<UserComment> findBySong(int songId);

    public String deleteComment(int commentId);
}
