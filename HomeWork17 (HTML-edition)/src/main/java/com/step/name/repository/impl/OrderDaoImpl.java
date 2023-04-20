package com.step.name.repository.impl;

import com.step.name.config.ConnectCreator;
import com.step.name.repository.OrderDAO;
import com.step.name.model.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class OrderDaoImpl implements OrderDAO {
    private static final Logger LOGGER = LogManager.getLogger(OrderDaoImpl.class);


    @Override
    public void createNewOrder(Order order) {
        try (Connection connection = ConnectCreator.getConnection()) {
            try (PreparedStatement statement = connection
                    .prepareStatement("insert into orders (user_id, total_prise) values (?,?)")) {
                statement.setInt(1, order.getUserId());
                statement.setBigDecimal(2,order.getTotalPrise());
                statement.executeUpdate();
            }
        } catch (SQLException exception) {
            LOGGER.error("SQLException at OrderDaoImpl at createNewOrder " + exception);
        }
    }



    @Override
    public void updateOrder(Order order) {
        try (Connection connection = ConnectCreator.getConnection()) {
            try (PreparedStatement st = connection.prepareStatement("update orders set total_prise  ="
                    + order.getTotalPrise() +"where id = " + order.getId())) {
                st.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException in method update" + e);
        }
    }

    @Override
    public Optional<Order> GetOrder(Order order) {
        Optional<Order>  crtOrder = Optional.empty();
        try (Connection connection = ConnectCreator.getConnection()) {
            try (PreparedStatement st = connection.prepareStatement(" select*from orders where user_id  =" + order.getUserId())) {
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    crtOrder = Optional.of(new Order(
                            rs.getInt("id"),
                            rs.getInt("user_id"),
                            rs.getBigDecimal("total_prise")));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException in method getOrder" + e);
        }
        return crtOrder;
    }

    @Override
    public BigDecimal getTotalPrice(Order order) {
        BigDecimal total = BigDecimal.ZERO;
        try (Connection connection = ConnectCreator.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "select sum(price) from goods inner join order_good " +
                    "on order_good.good_id = goods.id where order_id = " + order.getId()))
                     {
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    total = rs.getBigDecimal("sum(price)");
                }
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException in method getTotalPrice" + e);
        }
        return total;
    }

}