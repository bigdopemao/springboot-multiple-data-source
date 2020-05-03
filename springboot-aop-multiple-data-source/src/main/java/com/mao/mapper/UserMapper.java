package com.mao.mapper;

import com.mao.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author bigdope
 * @create 2020-01-10
 **/
@Mapper
@Component
public interface UserMapper {

    //使用xml配置形式查询
    List<User> getAllUser();

//    @Insert("insert into user(name,create_time) values(#{name},sysdate())")
    Long addUserGetID(User user);

    void addUser(@Param("name") String name);

}
