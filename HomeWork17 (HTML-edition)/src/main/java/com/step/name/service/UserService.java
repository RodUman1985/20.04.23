package com.step.name.service;

import com.step.name.model.User;

import java.util.Optional;

public interface UserService {
    void createUser(User user);

    Optional<User> getByName(String userName);
}
