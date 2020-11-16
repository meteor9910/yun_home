package com.hopu.mapper;

import com.hopu.pojo.User;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {


    @Select("SELECT * FROM t_user")
    List<User> findAll();

    void save(User user);
    @Select("SELECT * FROM t_user WHERE id= #{id}")
    User findById(Integer id);


    void update(User user);


    @Delete("DELETE FROM t_user WHERE id=#{id}")
    void deleteById(Integer id);
}