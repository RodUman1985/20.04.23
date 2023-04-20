package com.step.name.repository.impl;

import com.step.name.config.ConnectCreator;
import com.step.name.repository.UserDAO;
import com.step.name.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class UserDAOImpl implements UserDAO {
    private static final Logger LOGGER = LogManager.getLogger(UserDAOImpl.class);



    @Override
    public void createNewUser(User user) {
        try (Connection connection = ConnectCreator.getConnection()) {
            try (PreparedStatement statement = connection
                    .prepareStatement("INSERT INTO USERS (username, password) values (?,?)")) {
                statement.setString(1, user.getName());
                statement.setString(2, user.getPassword());
                statement.executeUpdate();
            }
        } catch (SQLException exception) {
            LOGGER.error("SQLException at UserDAOImpl at createNewUser " + exception);
        }
    }

    @Override
    public Optional<User> getUserByName(String userName) {
        Optional<User> optionalUser = Optional.empty();
        try (Connection connection = ConnectCreator.getConnection()) {
            try (PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM USERS WHERE USERNAME = '" + userName + "'")) {
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    optionalUser = Optional.of(new User(rs.getInt("ID"),
                            rs.getString("USERNAME"),
                            rs.getString("PASSWORD")));
                }
            }
        } catch (SQLException exception) {
            LOGGER.error("SQLException at UserDAOImpl at getUserByUsername " + exception);
        }
        return optionalUser;
    }
}
