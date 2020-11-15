package com.hopu.web.controller.admin;


import com.github.pagehelper.PageInfo;
import com.hopu.pojo.User;
import com.hopu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin/user")
public class AUserController {
    @Autowired
    private UserService userService;
//    分页查询所有用户
    @RequestMapping(value = "/list",name = "进入管理页面")
    public String findAll(@RequestParam(defaultValue = "1")  Integer pageNum,
                          @RequestParam(defaultValue = "5") Integer pageSize,
                          HttpServletRequest request) {
        PageInfo<User> pageInfo = userService.findPage(pageNum, pageSize);
        request.setAttribute("page",pageInfo);

        return  "admin/system/user/user_list";


    }

    @RequestMapping("toAddPage")
    public String toAddPage(HttpServletRequest request){
        return  "admin/system/user/user_add";

    }

//    添加用户
    @RequestMapping("/add")
    public String toAddPage(User user, HttpServletRequest request){
        user.setRole("管理员");
        userService.add(user);
        return  "redirect:/admin/user/list";
    }


    @RequestMapping("/toUpdatePage")

    public String toUpdatePage(Integer id, HttpServletRequest request){
        User user = userService.findById(id);
        request.setAttribute("user",user);
        return "admin/system/user/user_update";
    }
    //修改用户
    @RequestMapping("/update")
    public String update(User user, HttpServletRequest request) {
        userService.update(user);
        return "redirect:/admin/user/list";
    }

    @RequestMapping("/delete")
    public String delete(Integer id){
        userService.deleteById(id);
        return "redirect:/admin/user/list";
    }

}
