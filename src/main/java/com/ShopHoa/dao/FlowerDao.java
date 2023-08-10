package com.ShopHoa.dao;

import com.ShopHoa.entity.Flower;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FlowerDao {
    @Autowired
    public FlowerDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private EntityManager entityManager;
    public List<Flower> findAll() {
        return entityManager.createQuery("from Flower", Flower.class).getResultList();
    }
    //create function find by keyword(name)
    public List<Flower> findByName(String name){
        return entityManager.createQuery("from Flower where name like :name", Flower.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }
    //create function find by id
    public Flower findById(int id){
        return entityManager.find(Flower.class, id);
    }

    public List<Flower> findByCategory(int id){
        return entityManager.createQuery("from Flower where category.id = :id", Flower.class)
                .setParameter("id", id)
                .getResultList();
    }
    //create function find by category

    public Flower findFlowerAndCategoryByFlowerId(int id){
        return entityManager.createQuery("select f from Flower f "
                + "JOIN FETCH f.category "
                        + "where f.id = :id", Flower.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public void save(Flower flower){
        entityManager.merge(flower);
    }

     public void deleteById(int id)
     {
         entityManager.remove(entityManager.find(Flower.class, id));
     }




}
