package com.websitemonitor.notification;

import com.websitemonitor.model.enums.NotificationChannel;

public class NotificationFactory {
    public static NotificationSender getSender(NotificationChannel channel) {
        switch (channel) {
            case EMAIL:
                return new EmailNotificationSender();
            case SMS:
                // return new SmsNotificationSender();
            case PUSH_NOTIFICATION:
                // return new PushNotificationSender();
            default:
                throw new IllegalArgumentException("Unsupported notification channel");
        }
    }
}