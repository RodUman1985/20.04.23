package com.step.name.repository;

import com.step.name.model.User;

import java.util.Optional;

public interface UserDAO {

    void createNewUser(User user);

    Optional<User> getUserByName(String userName);
}
