package com.kk.ssm.service.Impl;

import com.kk.ssm.dao.IUserCommentDao;
import com.kk.ssm.entity.UserComment;
import com.kk.ssm.service.IUserCommentService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserCommentServiceImpl implements IUserCommentService{

    @Resource
    private IUserCommentDao iUserCommentDao;

    @Override
    public String addComment(int songId, String commentTime, String commentInf, int userNumber) {
        UserComment userComment = new UserComment();
        userComment.setSongId(songId);
        userComment.setCommentTime(commentTime);
        userComment.setCommentInf(commentInf);
        userComment.setUserNumber(userNumber);
        iUserCommentDao.addComment(userComment);
        return "success";
    }

    @Override
    public List<UserComment> findByUser(int userNumber) {
        return this.iUserCommentDao.findByUser(userNumber);
    }

    @Override
    public List<UserComment> findBySong(int songId) {
        return this.iUserCommentDao.findBySong(songId);
    }

    @Override
    public String deleteComment(int commentId) {
        iUserCommentDao.deleteByUser(commentId);
        return "success";
    }
}
