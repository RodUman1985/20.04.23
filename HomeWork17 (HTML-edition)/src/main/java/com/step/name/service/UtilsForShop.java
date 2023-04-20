package com.step.name.service;


import com.step.name.model.Order;
import com.step.name.model.User;

import javax.servlet.http.HttpServletRequest;


public final class UtilsForShop {

    private UtilsForShop() {
    }

    public static void setUser(HttpServletRequest request, User user) {
        request.getSession().setAttribute("user", user);
    }


    public static void setCheckStatus(HttpServletRequest request, String check) {
        request.getSession().setAttribute("check", check);
    }

    public static boolean isUsersEquals(HttpServletRequest request) {
        return ((User) request.getSession().getAttribute("user"))
                .getName().equals(request.getParameter("UserName"));
    }
    public  static void setOrder ( HttpServletRequest request, Order order){
        request.getSession().setAttribute("order",order);
    }
}

