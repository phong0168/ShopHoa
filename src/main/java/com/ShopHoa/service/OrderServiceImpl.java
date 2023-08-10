package com.ShopHoa.service;

import com.ShopHoa.dao.OrderDao;
import com.ShopHoa.entity.Order;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Transactional
    @Override
    public void save(Order order) {
        orderDao.save(order);
    }

    @Override
    public List<Order> findAll() {
        return orderDao.findAll();
    }

    @Override
    public Order findById(int id) {
        return orderDao.findById(id);
    }

    @Override
    public Order findOrderAndProductsByOrderId(int id) {
        return orderDao.findOrderAndProductsByOrderId(id);
    }


}
