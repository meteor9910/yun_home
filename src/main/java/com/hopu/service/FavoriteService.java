package com.hopu.service;

public interface FavoriteService {

    boolean findIfFavorite(Integer id, Integer userId);

    void cancleFavorite(Integer roomId, Integer id);

    void addFavorite(Integer roomId, Integer userId);
}
