package com.hopu.mapper;

import com.hopu.pojo.Room;

import java.util.List;

import com.hopu.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface RoomMapper {




    List<Room> findAll();

    void save(Room room);

    @Select("SELECT * FROM t_room WHERE id =#{id}")
    Room findById(Integer id);

    void update(Room room);



    @Delete("DELETE FROM t_room WHERE id =#{id}")
    void deleteById(Integer id);

    @Update("UPDATE t_room SET rent_status =#{rentStatus} WHERE id =#{id}")
    void updateRoom(@Param("id") Integer id, @Param("rentStatus") int rentStatus);

    List<Room> findUp(@Param("regionId") Integer regionId,@Param("beginRent") int beginRent,@Param("endRent") int endRent);
    List<Room> findAllByIds(List<Integer> roomIdList);

}