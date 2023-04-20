package com.step.name.repository.impl;

import com.step.name.config.ConnectCreator;
import com.step.name.repository.GoodDao;
import com.step.name.model.Good;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository

public class GoodDaoImpl implements GoodDao {
    private static final Logger LOGGER = LogManager.getLogger(GoodDaoImpl.class);



    @Override
    public Optional<Good> getByTittle(String tittle) {
        Optional<Good> good = Optional.empty();
        try (Connection connection = ConnectCreator.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement("select * from goods where tittle ='" + tittle + "'")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    good = Optional.of(new Good(rs.getInt("id"),
                            rs.getNString("tittle"),
                            rs.getBigDecimal("price")));
                }
            }
        } catch (SQLException throwables) {
            LOGGER.error("SQLException in method getByTittle" + throwables);
        }
        return good;
    }



    @Override
    public Optional<Good> getById(int id) {
        Optional<Good> good = Optional.empty();
        try (Connection connection = ConnectCreator.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM GOODS WHERE ID =" + id)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    good = Optional.of(new Good(rs.getInt(("ID")),
                            rs.getNString("TITTLE"),
                            rs.getBigDecimal("PRICE")));
                }
            }
        } catch (SQLException tes) {
            LOGGER.error("SQLException in method getID" + tes);
        }
        return good;
    }

    @Override
    public List<Good> getAll() {
        Good good = null;
        List<Good> goodList = new ArrayList<>();
        try (Connection connection = ConnectCreator.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM GOODS ")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    good = new Good(rs.getInt("ID"),
                            rs.getNString("TITTLE"),
                            rs.getBigDecimal("PRICE"));
                    goodList.add(good);
                }
            }
        } catch (SQLException throwables) {
            LOGGER.error("SQLException in method getAll" + throwables);
        }
        return goodList;
    }
}
