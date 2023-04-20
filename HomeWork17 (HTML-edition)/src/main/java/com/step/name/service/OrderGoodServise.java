package com.step.name.service;

import com.step.name.model.Good;
import com.step.name.model.Order;
import com.step.name.model.OrderGood;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface OrderGoodServise {

    String checkOrderGood(HttpServletRequest request);
    List<Good> getResultList(Order order);
}
