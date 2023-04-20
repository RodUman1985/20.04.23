package com.step.name.dao.impl;

import com.step.name.config.ConnectCreator;
import com.step.name.dao.OdrderGoodDao;

import com.step.name.model.Good;
import com.step.name.model.Order;
import com.step.name.model.OrderGood;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderGoodDaoImpl implements OdrderGoodDao {
    private static final Logger LOGGER = LogManager.getLogger(UserDAOImpl.class);



    @Override
    public Optional<OrderGood> getByOrder_Id(int order_id) {
        Optional<OrderGood> localOrder = Optional.empty();
        try (Connection connection = ConnectCreator.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM ORDER_GOOD WHERE ORDER_ID =" + order_id)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    localOrder = Optional.of(new OrderGood(rs.getInt("ID"),
                            rs.getInt("ORDER_ID"),
                            rs.getInt("GOOD_ID")));
                }
            }
        } catch (SQLException tes) {
            LOGGER.error("SQLException in method getByOrder_Id" + tes);
        }
        return localOrder;
    }

    @Override
    public Optional<OrderGood> getByGood_id(int good_id) {
        Optional<OrderGood> localOrder = Optional.empty();
        try (Connection connection = ConnectCreator.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM ORDER_GOOD WHERE GOOD_ID =" + good_id)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    localOrder = Optional.of(new OrderGood(rs.getInt("ID"),
                            rs.getInt("ORDER_ID"),
                            rs.getInt("GOOD_ID")));
                }
            }
        } catch (SQLException tes) {
            LOGGER.error("SQLException in method getByGood_id" + tes);
        }
        return localOrder;
    }

    @Override
    public void createOrderGood( int order, Good good) {
        try(Connection connection = ConnectCreator.getConnection()){
            try(PreparedStatement statement = connection.prepareStatement("INSERT INTO ORDER_GOOD (order_id, good_id) VALUES (?,?)")){
                statement.setInt(1, order);
                statement.setInt(2, good.getId());
                statement.executeUpdate();
            }

        }catch (SQLException e){
            LOGGER.error("SQLException at OrderGoodDAOImpl at createOrderGood");
        }

    }

    @Override
    public List<OrderGood> getAll(Order order) {
        OrderGood orderGood = null;
        List<OrderGood> orderGoodList = new ArrayList<>();
        try(Connection connection = ConnectCreator.getConnection()){
            try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM ORDER_GOOD WHERE ORDER_ID = "
                    + order.getId())){
                ResultSet rs = statement.executeQuery();
                while (rs.next()){
                    orderGood = new OrderGood(rs.getInt("ID"),
                            rs.getInt("ORDER_ID"),
                            rs.getInt("GOOD_ID"));
                    orderGoodList.add(orderGood);
                }
            }

        }catch (SQLException e){
            LOGGER.error("SQLException in method getAll at OrderGoodDAOImpl" + e);
        }
        return orderGoodList;
    }



}
