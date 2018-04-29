package com.kk.ssm.service.Impl;

import com.kk.ssm.dao.IUserDao;
import com.kk.ssm.entity.User;
import com.kk.ssm.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements IUserService{

    @Resource
    private IUserDao userDao;

    @Override
    public User login(int userNumber, String password) {
        User user = userDao.selectByUserNumber(userNumber);
        return user;
    }

    @Override
    public String insert(int userNumber, String userName, String password) {
        User user1 = new User();
        user1.setUserNumber(userNumber);
        user1.setUserName(userName);
        user1.setPassword(password);
        userDao.insert(user1);
        return "success";
    }

    @Override
    public User findByUserNumber(int userNumber) {
        return this.userDao.selectByUserNumber(userNumber);
    }


    /*@Override
    public User checkLogin(){
        return
    }*/

    public IUserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

}

