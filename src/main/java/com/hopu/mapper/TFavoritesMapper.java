package com.hopu.mapper;

import com.hopu.pojo.TFavorites;
import com.hopu.pojo.TFavoritesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TFavoritesMapper {
    long countByExample(TFavoritesExample example);

    int deleteByExample(TFavoritesExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TFavorites record);

    int insertSelective(TFavorites record);

    List<TFavorites> selectByExample(TFavoritesExample example);

    TFavorites selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TFavorites record, @Param("example") TFavoritesExample example);

    int updateByExample(@Param("record") TFavorites record, @Param("example") TFavoritesExample example);

    int updateByPrimaryKeySelective(TFavorites record);

    int updateByPrimaryKey(TFavorites record);
}