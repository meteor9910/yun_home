package com.hopu.web.controller.admin;


import com.hopu.pojo.User;
import com.hopu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private UserService userService;


    //跳转到用户界面
    @RequestMapping("/toRegisterPage")
    public String toRegisterPage(){
        //相应到用户界面
        return "register";
    }

    // 用户注册
    @RequestMapping("/register")
    public String register(User user, Model model){
        System.out.println(user);
        try {
            userService.add(user);
            return "login";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg","用户注册失败："+e.getMessage());
            return "register";
        }
    }

    @RequestMapping("/checkUserName")
    @ResponseBody
    public String checkUserName(String userName) {
        User user = userService.findByUserName(userName);
        if (user == null) {
            return "0";
            // 代表当前用户名未被注册

        }else {
            return "1";
            //代表当前用户注册了
        }

    }

    //发送手机验证码
    @RequestMapping("sendSMSCode")
    public  void  sendSMSCode(String telephone) {
        try {
            userService.sendSMSCode(telephone);
            // 暂时把生成的验证码放在sesion域对象,后期会使用redis
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("验证码发送失败");
        }

    }

    // 跳转到用户登录
    @RequestMapping("/toLoginPage")
    public String toLoginPage(){
        // 响应到用户列表页面
        return "login";
    }

    // 用户登录
    @RequestMapping("/login")
    public String login(User user, Model model, HttpServletRequest request){
        User user1 =userService.login(user);
        if(user1!=null){
            request.getSession().setAttribute("loginUser",user1);
            return "redirect:/index.jsp";
        }else{
            model.addAttribute("loginError_msg","用户名或密码错误！");
            return "login";
        }
    }

    // 用户退出
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("loginUser");
        return "redirect:/index.jsp";
    }





}
