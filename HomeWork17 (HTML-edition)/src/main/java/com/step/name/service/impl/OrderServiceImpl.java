package com.step.name.service.impl;


import com.step.name.repository.OrderDAO;

import com.step.name.model.Order;
import com.step.name.service.OrderService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDAO orderDao;

    public OrderServiceImpl(OrderDAO orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public void createNewOrder(Order order) {
       orderDao.createNewOrder(order);
    }

    @Override
    public void updateOrder(Order order) {
       orderDao.updateOrder(order);
    }

    @Override
    public Optional<Order> getOrder(Order order) {
        return orderDao.GetOrder(order);
    }


    public  BigDecimal getSumm(Order order) {
        BigDecimal summ = orderDao.getTotalPrice(order).setScale(2, RoundingMode.HALF_UP);
        order.setTotalPrise(summ);
        orderDao.updateOrder(order);

        return summ;
    }




}
