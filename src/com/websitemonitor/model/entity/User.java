package com.websitemonitor.model.entity;

import com.websitemonitor.model.enums.NotificationChannel;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String userId;
    private String username;
    private String email;
    private List<WebsiteSubscription> subscriptions;

    public User(String userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.subscriptions = new ArrayList<>();
    }

    // Getters and setters
    public String getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public List<WebsiteSubscription> getSubscriptions() { return subscriptions; }

    public void addSubscription(WebsiteSubscription subscription) {
        subscriptions.add(subscription);
    }

    public void removeSubscription(WebsiteSubscription subscription) {
        subscriptions.remove(subscription);
    }
}