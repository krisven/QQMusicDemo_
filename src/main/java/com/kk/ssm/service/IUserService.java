package com.kk.ssm.service;

import com.kk.ssm.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IUserService {
    public User login(int userNumber, String password);

    public String insert(int userNumber, String userName, String password);
    //public User checkLogin(HttpServletRequest request, HttpServletResponse response);

    public User findByUserNumber(int userNumber);
}
