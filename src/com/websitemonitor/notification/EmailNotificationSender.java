package com.websitemonitor.notification;

import com.websitemonitor.model.entity.WebsiteSubscription;

public class EmailNotificationSender implements NotificationObserver {
    @Override
    public void update(WebsiteSubscription subscription, String message) {
        System.out.println("Sending email to: " + subscription.getUser().getEmail());
        System.out.println("Message: " + message);
    }
}
