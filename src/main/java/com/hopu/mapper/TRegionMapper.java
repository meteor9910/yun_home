package com.hopu.mapper;

import com.hopu.pojo.TRegion;
import com.hopu.pojo.TRegionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRegionMapper {
    long countByExample(TRegionExample example);

    int deleteByExample(TRegionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TRegion record);

    int insertSelective(TRegion record);

    List<TRegion> selectByExample(TRegionExample example);

    TRegion selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TRegion record, @Param("example") TRegionExample example);

    int updateByExample(@Param("record") TRegion record, @Param("example") TRegionExample example);

    int updateByPrimaryKeySelective(TRegion record);

    int updateByPrimaryKey(TRegion record);
}