package com.ShopHoa.dao;

import com.ShopHoa.entity.Order;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDao {
    private EntityManager entityManager;

    @Autowired
    public OrderDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Order order){
        entityManager.merge(order);
    }

    public List<Order> findAll(){
        return entityManager.createQuery("from Order", Order.class).getResultList();
    }

    public Order findById(int id){
        return entityManager.find(Order.class, id);
    }

    //find order and products by order id
    public Order findOrderAndProductsByOrderId(int id){
        return entityManager.createQuery("select o from Order o join fetch o.flowerQuantities where o.id = :id", Order.class)
                .setParameter("id", id)
                .getSingleResult();
    }

}
