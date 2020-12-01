package com.hopu.web.interceptor;

import com.hopu.mapper.UserMapper;
import com.hopu.pojo.User;
import com.hopu.utils.CookieUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginInterceptor implements HandlerInterceptor {
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //判断用户是否登录
        Object login_user = request.getSession().getAttribute("loginUser");
        if (login_user == null) {
            //拦截到登陆页面
            response.sendRedirect(request.getContextPath()+"/user/toLoginPage");
            return false;

        }

        //已登录，放行
    return true;
}

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
