package com.hopu.service;

import com.github.pagehelper.PageInfo;
import com.hopu.pojo.Room;
import com.hopu.pojo.RoomImg;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RoomImgService {
    PageInfo<RoomImg> findPage(Integer pageNum, Integer pageSize);

//    List<Room> findAll();

    void add(RoomImg roomImg, MultipartFile[] roomImgs);

    List<Room> findAll();

    void delete(Integer id);

    List<RoomImg> findByRoomId(Integer id);






    void  update(Integer id, MultipartFile[] roomImgs);

    RoomImg findAll(int roomImgId);
}
