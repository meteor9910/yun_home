package com.hopu.mapper;

import com.hopu.pojo.Favorite;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface FavoriteMapper {


    @Select("select * from t_favorites where room_id=#{id} and user_id=#{userid}")
    Favorite findByRoomId(@Param("id") Integer id, @Param("userid") Integer userid);

    @Select("delete from t_favorites where room_id=#{roomId} and user_id=#{userId}")
    void deleteByRoomIdAndUserId(@Param("roomId") Integer roomId, @Param("userId") Integer userId);

    @Insert("insert into t_favorites (room_id,user_id,create_time) values (#{roomId},#{userId},#{createTime})")
    void addFavorite(@Param("roomId") Integer roomId, @Param("userId") Integer userId, @Param("createTime") Date createTime);

    @Select("select * from t_favorites where user_id =#{userId}")
    List<Favorite> findByUserId(Integer userId);
}