package com.websitemonitor.notification;

import com.websitemonitor.model.entity.User;

public class EmailNotificationSender implements NotificationSender {
    @Override
    public void sendNotification(User user, String message) {
        System.out.println("Sending email to: " + user.getEmail());
        System.out.println("Message: " + message);
        // Actual email sending implementation would go here
    }
}