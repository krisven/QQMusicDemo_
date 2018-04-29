package com.kk.ssm.dao;

import com.kk.ssm.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao {

    void insert(User user);

    int update(User user);

    int deleteByUserNumber(int userNumber);

    User login(int userNumber, String password);

    int selectByUserName(String userName);

    User selectByUserNumber(int userNumber);

}
