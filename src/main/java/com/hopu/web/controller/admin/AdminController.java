package com.hopu.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    //跳转到后台首页
    @RequestMapping("/toIndexPage")
    public String toIndexPage() {
        return "admin/home/main";

    }
    @RequestMapping("/home")
    public String toHomePage() {
        return  "admin/home/home";
    }


}
