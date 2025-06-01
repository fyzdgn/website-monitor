package com.websitemonitor.controller;

import com.websitemonitor.model.entity.User;
import com.websitemonitor.service.UserService;

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public User registerUser(String username, String email) {
        String userId = "user-" + System.currentTimeMillis();
        User user = new User(userId, username, email);
        userService.registerUser(user);
        return user;
    }

    public User getUser(String userId) {
        return userService.getUser(userId).orElse(null);
    }

    public void deleteUser(String userId) {
        userService.deleteUser(userId);
    }
}