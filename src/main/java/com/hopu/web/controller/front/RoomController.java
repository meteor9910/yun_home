package com.hopu.web.controller.front;


import com.github.pagehelper.PageInfo;
import com.hopu.pojo.Room;

import com.hopu.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/front/room")
public class RoomController {
    @Autowired
    private RoomService roomService;

    /**
     * 前台分页显示房屋信息
     */
    @ResponseBody
    @RequestMapping(value="list" ,name="分页查询所有房屋信息页面")
    public PageInfo findAll(@RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "10") Integer pageSize,
                            HttpServletRequest request){
        PageInfo<Room> pageInfo = roomService.findPage(pageNum,pageSize);
        System.out.println(1);
        return pageInfo;
    }
}
