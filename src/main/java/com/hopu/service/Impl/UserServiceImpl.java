package com.hopu.service.Impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hopu.mapper.UserMapper;
import com.hopu.pojo.User;
import com.hopu.service.UserService;
import com.hopu.utils.MD5Util;
import com.hopu.utils.RedisClient;
import com.hopu.utils.SmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo<User> findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> list = userMapper.findAll();
        return new PageInfo(list);
    }

    @Override
    public void add(User user) {
        String password = user.getPassword();
        //密码加密
        password = MD5Util.encodeByMd5(password);
        user.setPassword(password);
        user.setCreateTime(new Date());
        userMapper.save(user);
    }

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    public void update(User user) {
        user.setUpdateTime(new Date());
        userMapper.update(user);

    }

    @Override
    public void deleteById(Integer id) {
        userMapper.deleteById(id);
    }

    @Override
    public  void  sendSMSCode(String telephone){
        // 1、先生成6位的随机验证码
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 6;i++){
            stringBuffer.append(new Random().nextInt(9)+1);
        }
        String code = stringBuffer.toString();

        //发送短信
        SmsUtil.sendSms(telephone, code);
        // 3、发送成，就直接讲验证码存入Redis中
        RedisTemplate redisTemplate = RedisClient.getRedisTemplate();
        // 设置短信验证码有效期为1分钟（60s）
        redisTemplate.opsForValue().set("smscode",code,1, TimeUnit.MINUTES);

    }

    @Override
    public User findByUserName(String userName) {
        return null;
    }

    @Override
    public User login(User user) {
        String password = user.getPassword();
        password = MD5Util.encodeByMd5(password);
        User user1 = userMapper.findUserByNameAndPWD(user.getUsername(),password);
        return user1;
    }


}



