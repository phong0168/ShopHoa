package com.ShopHoa.service;

import com.ShopHoa.dao.FavoriteRepository;
import com.ShopHoa.entity.Favorite;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService{
    private final FavoriteRepository favoriteRepository;

    @Autowired
    public FavoriteServiceImpl(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    @Override
    public void save(Favorite favorite) {
        favoriteRepository.save(favorite);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        favoriteRepository.deleteById(id);
    }

    @Override
    public List<Favorite> findAllByUserId(int id) {
        return favoriteRepository.findAllByUserId(id);
    }

    @Override
    public boolean checkFavorite(int userId, int flowerId) {
        // Truy vấn cơ sở dữ liệu bằng SQL
        Favorite favorite = favoriteRepository.findByUserIdAndFlowerId(userId, flowerId);
        // Nếu có kết quả, nghĩa là đã yêu thích
        if (favorite != null) {
            return true;
        }
        // Ngược lại, nghĩa là chưa yêu thích
        else {
            return false;
        }
    }


}
