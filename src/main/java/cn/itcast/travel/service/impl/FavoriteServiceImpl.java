package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.service.FavoriteService;

public class FavoriteServiceImpl implements FavoriteService {
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();

    @Override
    public boolean findFavorite(String rid, int uid) {
        Favorite oneFavorite = favoriteDao.findOneFavorite(rid, uid);
        boolean isFavorite = false;
        if (oneFavorite!=null){
            isFavorite = true;
        }

        return isFavorite;

    }
}
