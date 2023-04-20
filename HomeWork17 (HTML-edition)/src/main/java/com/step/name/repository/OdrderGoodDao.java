package com.step.name.repository;

import com.step.name.model.Good;
import com.step.name.model.Order;
import com.step.name.model.OrderGood;

import java.util.List;
import java.util.Optional;

public interface OdrderGoodDao {

    Optional<OrderGood> getByOrder_Id(int order_id);
    Optional<OrderGood> getByGood_id(int good_id);
    void createOrderGood(int order, Good good);

    List<OrderGood> getAll(Order order);
}
