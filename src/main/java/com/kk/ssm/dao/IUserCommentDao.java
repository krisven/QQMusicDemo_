package com.kk.ssm.dao;

import com.kk.ssm.entity.UserComment;

import java.util.List;

public interface IUserCommentDao {
    void addComment(UserComment userComment);

    List<UserComment> findByUser(int userNumber);

    List<UserComment> findBySong(int songId);

    void deleteByUser(int commentId);
}
