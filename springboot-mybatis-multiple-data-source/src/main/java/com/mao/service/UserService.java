package com.mao.service;

import com.mao.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author bigdope
 * @create 2020-01-10
 **/
public interface UserService {

    List<User> getAllUser1();

    List<User> getAllUser2();

    Long addUserGetID1(User user);

    Long addUserGetID2(User user);

    void addUser1(@Param("name") String name);

    void addUser2(@Param("name") String name);
}
