package com.step.name.service.impl;

import com.step.name.repository.UserDAO;
import com.step.name.model.User;
import com.step.name.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO ;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void createUser(User user) {
        userDAO.createNewUser(user);
    }

    @Override
    public Optional<User> getByName(String userName) {
        return userDAO.getUserByName(userName);
    }
}
