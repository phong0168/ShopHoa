package com.ShopHoa.dao;

import com.ShopHoa.entity.Category;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDao {
    private EntityManager entityManager;

    @Autowired
    public CategoryDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Category> findAll() {
        return entityManager.createQuery("from Category", Category.class).getResultList();
    }
    public Category findById(int id){
        return entityManager.find(Category.class, id);
    }
}
