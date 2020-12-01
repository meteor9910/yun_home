package com.hopu.utils;

import com.hopu.mapper.UserMapper;
import com.hopu.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class Filter {
    @Autowired
    private UserMapper userMapper;
    public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //转为HttpServletRequest用以获取session
        HttpServletRequest request = (HttpServletRequest) req;
        //1.判断session里面是否还有user的值
        User user = (User) request.getSession().getAttribute("loginUser");
        if(user!= null) {
            System.out.println("filter不等于:"+user);
            //放行
            chain.doFilter(request, response);
        }
        else{
            //2.第一次登录
            Cookie[] cookies = request.getCookies();
            //找出自动登录的cookie
            Cookie cookie = CookieUtil.findCookie(cookies, "auto_login");
            if(cookie == null) {
                chain.doFilter(request, response);

            }else {
                //3.不是第一次登录分离出username 和password
                String value = cookie.getValue();
                String username = value.split("#")[0];
                String password = value.split("#")[1];

                user = userMapper.findUserByNameAndPWD (username, password);
                request.getSession().setAttribute("loginUse", user);
                //放行
                chain.doFilter(request, response);
            }
        }
    }

}
