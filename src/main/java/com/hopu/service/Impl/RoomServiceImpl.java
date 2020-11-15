package com.hopu.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hopu.pojo.Room;
import com.hopu.mapper.RoomMapper;
import com.hopu.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomMapper roomMapper;

    @Override
    public PageInfo<Room> findPage(Integer index, Integer size) {
        PageHelper.startPage(index, size);
        List<Room> List = roomMapper.findAll();
        return new PageInfo<>(List);
    }
}
