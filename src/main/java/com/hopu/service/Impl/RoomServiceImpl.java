package com.hopu.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hopu.mapper.RoomImgMapper;
import com.hopu.pojo.Room;
import com.hopu.mapper.RoomMapper;
import com.hopu.pojo.RoomImg;
import com.hopu.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


@Service
@Transactional
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private RoomImgMapper roomImgMapper;

    @Override
    public PageInfo<Room> findPage(Integer index, Integer size) {
        PageHelper.startPage(index, size);
        List<Room> List = roomMapper.findAll();
        return new PageInfo<>(List);
    }

    @Override
    public void add(Room room, MultipartFile[] uploadfiles) {
        roomMapper.save(room);
        // 判断并处理房屋文件上传
        //指定上传路径
        // 图片我们放在nginx服务器下的

        File file = new File("D:\\nginx-1.18.0\\nginx-1.18.0\\html");
        for (MultipartFile multipartFile:uploadfiles){
            //指定上传后的文件名称
            String newFileName = UUID.randomUUID()+multipartFile.getOriginalFilename();
            //文件上传
            try {
                multipartFile.transferTo(new File(file,newFileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            roomImgMapper.add(room.getId(),newFileName);


        }


    }

    @Override
    public Room findById(Integer id) {
        return roomMapper.findById(id);
    }

    @Override
    public void update(Room room) {
        // 一旦对房源进行修改，要将原本上架的房源改为下架
       room.setRentStatus(-1);
       roomMapper.update(room);

    }



    @Override
    public void delete(Integer id) {
        roomMapper.deleteById(id);
        // 同时删除数据库图片
        List<RoomImg> roomImgList =roomImgMapper.findByRoomId(id);
        roomImgMapper.deleteByRoomId(id);
        roomImgList.forEach(roomImg -> {
            String img = roomImg.getImg();
            // 同时删除nginx服务器上图片
            File file = new File("D:\\nginx-1.18.0\\nginx-1.18.0\\html",img);
            if(file.exists()){
                file.delete();
            }
        });
    }

    @Override
    public void updateRoomRentStatus(Integer id, int rentStatus) {
        roomMapper.updateRoom(id,rentStatus);
    }


}
