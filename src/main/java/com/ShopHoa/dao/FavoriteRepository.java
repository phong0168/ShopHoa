package com.ShopHoa.dao;

import com.ShopHoa.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    @Query("SELECT f FROM Favorite f WHERE f.user.id = ?1")
    List<Favorite> findAllByUserId(int id);

    Favorite findByUserIdAndFlowerId(int userId, int flowerId);
}