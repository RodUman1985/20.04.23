package com.step.name.model;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Objects;



public class Order {

    private int id;

    private int userId;

    private BigDecimal totalPrise;

    public Order(int id) {
        this.id = id;
    }

    public Order(int id, int userId) {
        this.id = id;
        this.userId = userId;
    }

    public Order(int id, int userId, BigDecimal totalPrise) {
        this.id = id;
        this.userId = userId;
        this.totalPrise = totalPrise;
    }

    public Order(int userId, BigDecimal bigDecimal) {
        this.userId = userId;
        this.totalPrise = bigDecimal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public BigDecimal getTotalPrise() {
        return totalPrise;
    }

    public void setTotalPrise(BigDecimal totalPrise) {
        this.totalPrise = totalPrise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                userId == order.userId &&
                Objects.equals(totalPrise, order.totalPrise);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, totalPrise);
    }

    @Override
    public String toString() {
        return "OrderDAO{" +
                "id=" + id +
                ", userId=" + userId +
                ", totalPrise=" + totalPrise +
                '}';
    }
}
