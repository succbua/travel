package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;

public interface FavoriteDao {
    Favorite  findOneFavorite(String rid, int uid);
}
