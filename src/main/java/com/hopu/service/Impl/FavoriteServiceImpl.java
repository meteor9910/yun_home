package com.hopu.service.Impl;


import com.hopu.mapper.FavoriteMapper;
import com.hopu.pojo.Favorite;
import com.hopu.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    private FavoriteMapper favoriteMapper;

    @Override
    public boolean findIfFavorite(Integer id, Integer userId) {
        return favoriteMapper.findByRoomId(id,userId) == null?true : false;
    }

    @Override
    public void cancleFavorite(Integer roomId, Integer userId) {
        favoriteMapper.deleteByRoomIdAndUserId(roomId, userId);


    }

    @Override
    public void addFavorite(Integer roomId, Integer userId) {
        // 添加收藏之前可以进行判断，是否收藏
    Favorite favorite = favoriteMapper.findByRoomId(roomId, userId);
    if (favorite == null) {
        favoriteMapper.addFavorite(roomId, userId,new Date());
    }

    }
}
