package com.hopu.mapper;

import com.hopu.pojo.Region;
import com.hopu.pojo.RegionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface RegionMapper {


    @Select("SELECT * FROM t_region")
    List<Region> findAll();

    @Select("select * from t_region where id =#{regionId}")
    Region findById(Integer regionId);
}