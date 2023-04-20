package com.step.name.service.impl;


import com.step.name.repository.GoodDao;
import com.step.name.repository.OdrderGoodDao;
import com.step.name.repository.OrderDAO;


import com.step.name.model.Good;
import com.step.name.model.Order;
import com.step.name.model.OrderGood;
import com.step.name.service.OrderGoodServise;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service

public class OrderGoodServiseImp implements OrderGoodServise {


    private final OdrderGoodDao orderGood ;
    private final GoodDao goodDao ;
    private final OrderDAO orderDAO;

    public OrderGoodServiseImp(OdrderGoodDao orderGood, GoodDao goodDao, OrderDAO orderDAO) {
        this.orderGood = orderGood;
        this.goodDao = goodDao;
        this.orderDAO = orderDAO;
    }


    @Override
    public String checkOrderGood(HttpServletRequest request) {
        if (request.getParameter("select") == null) {
            return "Make your order:";
        } else {
            String srcGood = request.getParameter("select");
            String res = srcGood.substring(0, srcGood.lastIndexOf(" "));
            Order order = (Order) request.getSession().getAttribute("order");
            Optional<Good> good = goodDao.getByTittle(res);
            orderGood.createOrderGood(order.getId(), good.orElseThrow());
            request.getSession().setAttribute("ResultList", getResultList(order));

        }

        return " choose:";
    }

    @Override
    public List<Good> getResultList(Order order) {
        List<OrderGood> list = orderGood.getAll(order);
        List<Good> result = new ArrayList<>();
        for (OrderGood e : list) {
            Optional<Good> good = goodDao.getById(e.getGood_id());
            result.add(good.orElseThrow());
        }
        return result;
    }


}
