package com.hopu.mapper;

import com.hopu.pojo.Room;
import com.hopu.pojo.RoomImg;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RoomImgMapper {
    @Insert("INSERT INTO t_room_img(img,room_id) VALUES (#{s},#{id})")
    void add(@Param("id") Integer id, @Param("s") String s);

    @Select("SELECT * FROM t_room_img WHERE room_id =#{id}")
    List<RoomImg> findByRoomId(Integer id);

    @Delete("DELETE FROM t_room_img WHERE room_id =#{id}")
    void deleteByRoomId(Integer id);

    List<RoomImg> findAll();

    @Select("SELECT * FROM t_room_img WHERE id =#{id}")
    RoomImg fingById(Integer id);

    @Delete("DELETE FROM t_room_img WHERE id =#{id}")
    void delete(Integer id);



}
