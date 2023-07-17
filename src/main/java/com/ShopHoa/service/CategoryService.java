package com.ShopHoa.service;

import com.ShopHoa.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findById(int id);
}
