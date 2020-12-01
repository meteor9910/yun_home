package com.hopu.web.controller;

import com.hopu.mapper.UserMapper;
import com.hopu.pojo.User;

import com.hopu.service.UserService;
import com.hopu.utils.MD5Util;
import com.hopu.utils.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired


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



    @RequestMapping("/loginCustomer")
    public String loginCustomer(HttpServletRequest request, HttpServletResponse response) {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String auto_login = request.getParameter("auto_login");
        String cookieFlag = request.getParameter("auto_login");
        //根据用户和密码查找用户

        String newPwd = MD5Util.encodeByMd5(password);

//        User user = userService.findUserByNameAndPWD(username, newPwd);
        User user = userMapper.findUserByNameAndPWD(username,newPwd);

        //判断是否有该用户
        if(user!=null) {
            if("on".equals(auto_login)) {
                //把用户名和密码放入cookie返回给客户端
                Cookie cookie =new Cookie("auto_login", username+"+"+password);
                //设置7天有效期
                cookie.setMaxAge(60*60*24*7);
                //设置有效路径
                cookie.setPath(request.getContextPath());
                //response把cookie返回给客户端
                response.addCookie(cookie);
            }
            //把user存放在session中
            HttpSession session = request.getSession();
            session.setAttribute("loginUser", user);


//            if ("1".equals(cookieFlag)) {
//                String loginInfo = username + "," + password;
//                Cookie userCookie = new Cookie("loginInfo", loginInfo);
//                userCookie.setMaxAge(1 * 24 * 60 * 60); // 存活期为一天 1*24*60*60
//                userCookie.setPath("/");
//                request.addCookie(userCookie);
//            }

            //进入登录后的页面
            return "redirect:/index.jsp";

        }
        else {
            return "forward:login";
        }









    }






}
