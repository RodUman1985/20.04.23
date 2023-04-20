package com.step.name.controllers;


import com.step.name.model.Order;
import com.step.name.model.User;
import com.step.name.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

@Controller
@RequestMapping("/Shop")
public class ShopFirstController {

    private static final Logger LOGGER = LogManager.getLogger(ShopFirstController.class);

    private final OrderService orderService;
    private final GoodService goodService;
    private final OrderGoodServise orderGoodServise;
    private final UserService userService;

    @Autowired
    public ShopFirstController(OrderService orderService, GoodService goodService,
                               OrderGoodServise orderGoodServise, UserService userService) {
        this.orderService = orderService;
        this.goodService = goodService;
        this.orderGoodServise = orderGoodServise;
        this.userService = userService;
    }

    /*
     public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse
            , FilterChain filterChain) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;
        request.setAttribute("goods", goodService.getGoods());
        if (request.getParameter("select") != null){};
        checkUser(request);
        checkFlag(response, request);
        checkOrder(request);
        try {
            filterChain.doFilter(request, response);
        } catch (IOException e) {
            LOGGER.error("IOException in doFilter " + e);
        } catch (ServletException e) {
            LOGGER.error("ServletException in doFilter " + e);
        }
    }
    }*/
    @PostMapping()
    public ModelAndView doOrder(HttpServletRequest request , HttpServletResponse response) {
        final ModelAndView modelAndView = new ModelAndView("Shop");
       // HttpServletRequest request = (HttpServletRequest) servletRequest;
       // HttpServletRequest response = (HttpServletRequest) servletResponse;
        checkAllParam(request, response);
        User user = (User) request.getSession().getAttribute("user");
        Order order = (Order) request.getSession().getAttribute("order");
        modelAndView.addObject("goods", goodService.printGoods());
        modelAndView.addObject("checkOrder", orderGoodServise.checkOrderGood(request));
        modelAndView.addObject("ResultList", orderGoodServise.getResultList(order));
        return modelAndView;
    }

    private void checkAllParam(ServletRequest servletRequest, ServletResponse servletResponse) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        checkUser(request);
        checkFlag(servletResponse, request);
        checkOrder(request);

    }

    private void checkFlag(ServletResponse servletResponse, HttpServletRequest request) {
        if (request.getSession().getAttribute("check") == null) {
            if (request.getParameter("check") != null) {
                UtilsForShop.setCheckStatus(request, request.getParameter("check"));
            } else {
                String path = "/ErrorPage";
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
                try {
                    requestDispatcher.forward(request, servletResponse);
                } catch (ServletException e) {
                    LOGGER.error("ServletException in checkFlag" + e);
                } catch (IOException e) {
                    LOGGER.error("IOException in checkFlag" + e);
                }
            }
        }
    }

    private void checkUser(HttpServletRequest request) {
        String name = request.getParameter("UserName");
        String password = request.getParameter("pass");
        User user = new User(name, password);
        if (name != null) {
            if (userService.getByName(name).isEmpty()) {
                userService.createUser(user);
            }
            Optional<User> newUser = userService.getByName(name);
            if (newUser.isPresent()) {
                if (request.getSession().getAttribute("user") == null) {
                    UtilsForShop.setUser(request, newUser.get());
                } else if (!UtilsForShop.isUsersEquals(request)) {
                    request.getSession().invalidate();
                    UtilsForShop.setUser(request, newUser.get());
                }
            }
        }
    }

    private void checkOrder(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Order crtOrder = new Order(user.getId(), BigDecimal.ZERO);
        if (request.getSession().getAttribute("order") == null) {
           /* orderDAO.createNewOrder(crtOrder);
            UtilsForShop.setOrder(request,orderDAO.GetOrder(crtOrder).orElseThrow());*/
            orderService.createNewOrder(crtOrder);
            UtilsForShop.setOrder(request, orderService.getOrder(crtOrder).orElseThrow());
            System.out.println(request.getSession().getAttribute("order").toString());
        }

    }

}
