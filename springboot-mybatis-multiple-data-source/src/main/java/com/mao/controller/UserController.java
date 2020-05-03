package com.mao.controller;

import com.mao.model.User;
import com.mao.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author bigdope
 * @create 2020-01-10
 **/
@Api("用户")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("db1 - 查询所有用户")
    @RequestMapping(value = "/getDb1AllUser", method = RequestMethod.GET)
    public List<User> getDb1AllUser1() {
        return userService.getAllUser1();
    }

    @ApiOperation("db2 - 查询所有用户")
    @RequestMapping(value = "/getDb2AllUser", method = RequestMethod.GET)
    public List<User> getDb2AllUser2() {
        return userService.getAllUser2();
    }

    @ApiOperation("查询所有用户")
    @RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
    public List<User> getAllUser() {
        List<User> user1 = userService.getAllUser1();
        List<User> user2 = userService.getAllUser2();
        user1.addAll(user2);
        return user1;
    }

    @ApiOperation("db1 - 保存")
    @RequestMapping(value = "/addDb1User", method = RequestMethod.GET)
    public String addDb1User(String name) {
        User user = new User(name,new Date());
        Long rows = userService.addUserGetID1(user);//返回的是结果行数
        return "{id:"+user.getId()+"}";
    }

    @ApiOperation("db2 - 保存")
    @RequestMapping(value = "/addDb2User", method = RequestMethod.GET)
    public String addDb2User(String name) {
        User user = new User(name,new Date());
        Long rows = userService.addUserGetID2(user);
        return "{id:"+user.getId()+"}";
    }

}
