package com.ShopHoa.controller;

import com.ShopHoa.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FavoriteController {
    private final FavoriteService favoriteService;

    @Autowired
    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    // Xử lý yêu cầu GET /favorite/check?user_id={user_id}&flower_id={flower_id}
    @GetMapping("/favorite/check")
    public ResponseEntity<Boolean> checkFavorite(@RequestParam("user_id") int user_id,
                                                 @RequestParam("flower_id") int flower_id) {
        // Gọi phương thức của service để kiểm tra xem tài khoản đã yêu thích sản phẩm đó hay chưa
        boolean isFavorite = favoriteService.checkFavorite(user_id, flower_id);
        // Trả về kết quả dưới dạng JSON với mã trạng thái 200 OK
        return new ResponseEntity<>(isFavorite, HttpStatus.OK);
    }
}

