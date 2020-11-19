package com.hopu.service.Impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hopu.mapper.RoomImgMapper;
import com.hopu.mapper.RoomMapper;
import com.hopu.pojo.Room;
import com.hopu.pojo.RoomImg;
import com.hopu.pojo.User;
import com.hopu.service.RoomImgService;
import com.hopu.service.RoomService;
import com.hopu.service.UserService;
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
public class RoomImgServiceImpl implements RoomImgService {
    @Autowired
    private RoomImgMapper roomImgMapper;

    @Override
    public PageInfo<RoomImg> findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<RoomImg> list = roomImgMapper.findAll();
        return new PageInfo(list);
    }



    @Override
    public void add(RoomImg roomImg, MultipartFile[] roomImgs) {
        // 判断并处理房屋文件上传
        //指定上传路径
        // 图片我们放在nginx服务器下
        File file = new File("D:\\nginx-1.18.0\\nginx-1.18.0\\html");
        for (MultipartFile multipartFile : roomImgs){
            //指定文件上传名称
            String newFileName = UUID.randomUUID()+multipartFile.getOriginalFilename();
            try {
                multipartFile.transferTo(new File(file,newFileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            //保存图片
            roomImgMapper.add(roomImg.getRoomId(),newFileName);
        }
    }
@Autowired
private RoomMapper roomMapper;
    @Override
    public List<Room> findAll() {
       return roomMapper.findAll();
    }

    @Override
    public void delete(Integer id) {
         RoomImg roomImg   =  roomImgMapper.fingById(id);
        roomImgMapper.delete(id);
        //从ngix服务器上移除图片
        //并且从ngix服务器上删除图片
        File file = new File("D:\\nginx-1.18.0\\nginx-1.18.0\\html",roomImg.getImg());
        if (file.exists()){
            file.delete();
        }


    }





    @Override
    public void  update(Integer id, MultipartFile[] roomImgs){
        RoomImg roomImg   =  roomImgMapper.fingById(id);
        roomImgMapper.delete(id);

        File file = new File("D:\\nginx-1.18.0\\nginx-1.18.0\\html",roomImg.getImg());
        if (file.exists()){
            file.delete();
        }

        File file1 = new File("D:\\nginx-1.18.0\\nginx-1.18.0\\html");
        for (MultipartFile multipartFile : roomImgs){
            //指定文件上传名称
            String newFileName = UUID.randomUUID()+multipartFile.getOriginalFilename();
            try {
                multipartFile.transferTo(new File(file1,newFileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            //保存图片
            roomImgMapper.add(roomImg.getRoomId(),newFileName);
        }



    }

    @Override
    public RoomImg findAll(int roomImgId) {
        return roomImgMapper.fingById(roomImgId);
    }
}
