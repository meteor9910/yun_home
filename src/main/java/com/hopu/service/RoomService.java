package com.hopu.service;

import com.github.pagehelper.PageInfo;
import com.hopu.pojo.Room;


public interface RoomService {
    PageInfo<Room> findPage(Integer pageNum, Integer pageSize);
}
