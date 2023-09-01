package com.ShopHoa.service;

import com.ShopHoa.entity.Favorite;

import java.util.List;

public interface FavoriteService {
    void save(Favorite favorite);
    void deleteById(int id);
    List<Favorite> findAllByUserId(int id);

}
