package com.ShopHoa.service;

import com.ShopHoa.entity.Flower;

import java.util.List;

public interface FlowerService {
    List<Flower> findAll();
    List<Flower> findByName(String name);
    Flower findById(int id);
    List<Flower> findByCategory(int id);
    void save(Flower flower);
    void deleteById(int id);
}
