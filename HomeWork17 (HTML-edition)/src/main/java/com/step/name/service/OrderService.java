package com.step.name.service;

import com.step.name.model.Order;

import java.math.BigDecimal;
import java.util.Optional;

public interface OrderService {

    void createNewOrder(Order order);


    void updateOrder (Order order);
    Optional<com.step.name.model.Order > getOrder (Order order);

}
