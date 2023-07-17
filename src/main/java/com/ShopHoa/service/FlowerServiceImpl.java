package com.ShopHoa.service;

import com.ShopHoa.dao.FlowerDao;
import com.ShopHoa.entity.Flower;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlowerServiceImpl implements FlowerService {
    private FlowerDao flowerDao;

    @Autowired
    public FlowerServiceImpl(FlowerDao flowerDao) {
        this.flowerDao = flowerDao;
    }


    @Override
    public List<Flower> findAll(){
        return flowerDao.findAll();
    }
    @Override

    public List<Flower> findByName(String name){
        return flowerDao.findByName(name);
    }
    @Override
    public Flower findById(int id){
        return flowerDao.findById(id);
    }
    @Override
    public List<Flower> findByCategory(int id){
        return flowerDao.findByCategory(id);
    }

    @Override
    public Flower findFlowerAndCategoryByFlowerId(int id){
        return flowerDao.findFlowerAndCategoryByFlowerId(id);
    }

    @Transactional
    @Override
    public void save(Flower flower) {
        flowerDao.save(flower);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        flowerDao.deleteById(id);
    }
}
