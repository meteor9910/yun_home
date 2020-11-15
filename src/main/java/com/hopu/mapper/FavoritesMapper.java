package com.hopu.mapper;

import com.hopu.pojo.Favorites;
import com.hopu.pojo.FavoritesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FavoritesMapper {
    long countByExample(FavoritesExample example);

    int deleteByExample(FavoritesExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Favorites record);

    int insertSelective(Favorites record);

    List<Favorites> selectByExample(FavoritesExample example);

    Favorites selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Favorites record, @Param("example") FavoritesExample example);

    int updateByExample(@Param("record") Favorites record, @Param("example") FavoritesExample example);

    int updateByPrimaryKeySelective(Favorites record);

    int updateByPrimaryKey(Favorites record);
}