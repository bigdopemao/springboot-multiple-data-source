package com.mao.service;

import com.mao.mapper.db1.UserMapper1;
import com.mao.mapper.db2.UserMapper2;
import com.mao.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bigdope
 * @create 2020-01-10
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper1 userMapper1;

    @Autowired
    private UserMapper2 userMapper2;

    @Override
    public List<User> getAllUser1() {
        return userMapper1.getAllUser();
    }

    @Override
    public List<User> getAllUser2() {
        return userMapper2.getAllUser();
    }

    @Override
    public Long addUserGetID1(User user) {
        return userMapper1.addUserGetID(user);
    }

    @Override
    public Long addUserGetID2(User user) {
        return userMapper2.addUserGetID(user);
    }

    @Override
    public void addUser1(String name) {
        userMapper1.addUser(name);
    }

    @Override
    public void addUser2(String name) {
        userMapper2.addUser(name);
    }
}
