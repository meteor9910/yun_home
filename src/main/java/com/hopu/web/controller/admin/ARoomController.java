package com.hopu.web.controller.admin;


import com.github.pagehelper.PageInfo;
import com.hopu.pojo.Room;
import com.hopu.pojo.User;
import com.hopu.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Tomotake Yoshino
 * 房屋管理
 */
@Controller
@RequestMapping("/admin/room")
public class ARoomController {
    @Autowired
    private RoomService roomService;

//    分页查询所有房屋讯息
    @RequestMapping(value = "/list",name = "进入房屋管理页面")
    public  String findAll(@RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "10") Integer pageSize,
                           HttpServletRequest request) {
        // todo 只能查看自己发发布的房源信息（管理员除外）
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        Integer userId = loginUser.getId();
        if("管理员".equals(loginUser.getRole())){
            userId = null;

        }
        PageInfo<Room> pageInfo = roomService.findPage(pageNum, pageSize);
        request.setAttribute("page",pageInfo);
        return "admin/system/room/room_list";


    }
    @RequestMapping("toAddPage")
    public String toAddPage(){
        return  "admin/system/room/room_add";
    }

//    添加房源信息

   @RequestMapping("/add")
    public String add(Room room , MultipartFile[] roomImgs,HttpServletRequest request) throws IOException {
        //设置发布房源当前用户
//       room.setUserId(3);
       // todo 后续需要修改为从session域中获取当前登录用户信息
       User loginUser = (User) request.getSession().getAttribute("loginUser");
       room.setUserId(loginUser.getId());

       room.setRentStatus(-1);
       roomService.add(room,roomImgs);
       return "redirect:/admin/room/list";
    }

    @RequestMapping("/toUpdatePage")
    public String toUpdatePage(Integer id,HttpServletRequest request){
        Room room = roomService.findById(id);
        request.setAttribute("room",room);
        return "admin/system/room/room_update";

    }

    @RequestMapping("/update")
    public String update(Room room){
        roomService.update(room);
        return "redirect:/admin/room/list";
    }

    @RequestMapping("/delete")
    public String delete(Integer id) {
        roomService.delete(id);
        return "redirect:/admin/room/list";
    }

    /**
     * 房源上架
     */
    @RequestMapping("/up")
    public String up(Integer id) {
        roomService.updateRoomRentStatus(id,0);
        return "redirect:/admin/room/list";
    }
    /**
     * 房源下架
     */
    @RequestMapping("/down")
    public String down(Integer id) {
        roomService.updateRoomRentStatus(id,-1);
        return "redirect:/admin/room/list";
    }







}
