package com.hopu.web.controller;

import com.hopu.pojo.User;

import com.hopu.service.UserService;
import com.hopu.utils.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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

    // 跳转到用户注册页面
    @RequestMapping("/toRegisterPage")
    public String toRegisterPage(){
        // 响应到用户列表页面
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

    // 发送手机短信验证码
    @RequestMapping("/sendSMSCode")
    public void sendSMSCode(String telephone) {
        try {
            userService.sendSMSCode(telephone);
            // 暂时把生成的验证码放在sesion域对象,后期会使用redis
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("验证码发送失败！");
        }
    }


    /**
     * 手机验证码校验
     */
    @RequestMapping("/checkCode")
    @ResponseBody
    public String checkCode(String smsCode) {
        RedisTemplate redisTemplate = RedisClient.getRedisTemplate();
        Object o = redisTemplate.opsForValue().get("smscode");
        if(o==null){
            return "验证码过期";  // 表示验证码过期
        }else {
            if(smsCode.equals(o)){
                return "ok";  // 表示验证码没有问题
            }else {
                return "验证码错误";  // 表示验证码错误
            }
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
    public String login(User user, Model model,HttpServletRequest request){
        User user1 =userService.login(user);
        if(user1!=null){
            request.getSession().setAttribute("loginUser",user1);
            return "redirect:/index.jsp";
        }else{
            model.addAttribute("loginError_msg","用户名或密码错误！");
            return "login";
        }
    }
    // 用户异步登录
    @RequestMapping("/asyncLogin")
    @ResponseBody
    public String asyncLogin(User user, Model model,HttpServletRequest request){
        User user1 =userService.login(user);
        if(user1!=null){
            request.getSession().setAttribute("loginUser",user1);
            return user1.getUsername();
        }else{
            return "-1";
        }
    }

    // 用户退出
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("loginUser");
        return "redirect:/index.jsp";
    }

}
