package com.step.name.dao;

import com.step.name.model.Order;

import java.math.BigDecimal;
import java.util.Optional;

public interface OrderDAO {

    void createNewOrder(Order order);
    void updateOrder (Order order);
    Optional<Order > GetOrder (Order order);
    BigDecimal getTotalPrice(Order order);


}
