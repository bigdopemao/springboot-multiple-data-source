package com.mao.service;

import com.mao.config.TargetDataSource;
import com.mao.mapper.UserMapper;
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
    private UserMapper userMapper;

    @TargetDataSource("dataSource1")
    @Override
    public List<User> getAllUser1() {
        return userMapper.getAllUser();
    }

    @TargetDataSource("dataSource2")
    @Override
    public List<User> getAllUser2() {
        return userMapper.getAllUser();
    }

    @TargetDataSource("dataSource1")
    @Override
    public Long addUserGetID1(User user) {
        return userMapper.addUserGetID(user);
    }

    @TargetDataSource("dataSource2")
    @Override
    public Long addUserGetID2(User user) {
        return userMapper.addUserGetID(user);
    }

    @TargetDataSource("dataSource1")
    @Override
    public void addUser1(String name) {
        userMapper.addUser(name);
    }

    @TargetDataSource("dataSource2")
    @Override
    public void addUser2(String name) {
        userMapper.addUser(name);
    }
}
