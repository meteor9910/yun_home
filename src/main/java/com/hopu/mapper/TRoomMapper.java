package com.hopu.mapper;

import com.hopu.pojo.TRoom;
import com.hopu.pojo.TRoomExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRoomMapper {
    long countByExample(TRoomExample example);

    int deleteByExample(TRoomExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TRoom record);

    int insertSelective(TRoom record);

    List<TRoom> selectByExample(TRoomExample example);

    TRoom selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TRoom record, @Param("example") TRoomExample example);

    int updateByExample(@Param("record") TRoom record, @Param("example") TRoomExample example);

    int updateByPrimaryKeySelective(TRoom record);

    int updateByPrimaryKey(TRoom record);
}