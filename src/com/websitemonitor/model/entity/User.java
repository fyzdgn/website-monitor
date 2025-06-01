package com.websitemonitor.model.entity;

import com.websitemonitor.model.enums.NotificationChannel;
import com.websitemonitor.notification.NotificationObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user in the system who subscribes to website updates.
 * Implements NotificationObserver to receive update notifications.
 */
public class User implements NotificationObserver {
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

    // Observer Pattern: Called when a subscribed website is updated
    @Override
    public void update(WebsiteSubscription subscription, String message) {
        System.out.println("User '" + username + "' received update for " +
                subscription.getUrl() + ": " + message);
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
