package com.hopu.web.controller.admin;


import com.github.pagehelper.PageInfo;
import com.hopu.pojo.Room;
import com.hopu.pojo.RoomImg;
import com.hopu.service.RoomImgService;
import com.hopu.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/roomImg")
public class ARoomImgController {
    @Autowired
    private RoomImgService roomImgService;
    @Autowired
    private RoomService roomService;
    /**
     * 分页查询所有房屋信息
     */
    @RequestMapping(value = "/list",name = "进入房屋图片管理页面")
    public String findAll(@RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(defaultValue = "10") Integer pageSize,
                          HttpServletRequest request) {
        PageInfo<RoomImg> pageInfo = roomImgService.findPage(pageNum, pageSize);
        request.setAttribute("page",pageInfo);
        return "admin/system/roomImg/roomImg_list";

    }

    /**
     *
     * 添加房屋，需要知道自己给哪个房屋添加，可以采用异步或者同步的方式来添加
     */
    @RequestMapping("toAddPage")
    public String toAddPage(HttpServletRequest request){
        List<Room> roomList = roomImgService.findAll();
        request.setAttribute("rooms",roomList);
        return "admin/system/roomImg/roomImg_add";


    }

    /**
     * 添加房源信息
     */
    @RequestMapping("/add")
    private String add(RoomImg roomImg, MultipartFile[] roomImgs)
    throws IOException {
        roomImgService.add(roomImg, roomImgs);
        return "redirect:/admin/roomImg/list";

    }

    @RequestMapping("delete")
    private String delete(Integer id){
        roomImgService.delete(id);

        return "redirect:/admin/roomImg/list";

    }

    @RequestMapping("/toUpdatePage")
    private  String toUpdatePage(int roomImgId,HttpServletRequest request)

        throws IOException{

        RoomImg roomImg = roomImgService.findAll(roomImgId);
        request.setAttribute("roomImg",roomImg);

       return "admin/system/roomImg/roomImg_update";



    }

    @RequestMapping("/update")
    private String update(Integer roomImgId,MultipartFile[] roomImgs){


     roomImgService.update(roomImgId, roomImgs);


        return "redirect:/admin/roomImg/list";

    }


}
