package com.ShopHoa.service;

import com.ShopHoa.dao.OrderDao;
import com.ShopHoa.entity.Order;

import java.util.List;

public interface OrderService {
    void save(Order order);
    List<Order> findAll();

    Order findById(int id);
    Order findOrderAndProductsByOrderId(int id);
}
