package com.hopu.service;

import com.github.pagehelper.PageInfo;
import com.hopu.pojo.User;

public interface UserService {
    PageInfo<User> findPage(Integer pageNum, Integer pageSize);

    void add(User user);


    User findById(Integer id);

    void update(User user);

    void deleteById(Integer id);
}
