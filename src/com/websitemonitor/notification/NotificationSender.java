package com.websitemonitor.notification;

import com.websitemonitor.model.entity.User;
import com.websitemonitor.model.enums.NotificationChannel;

public interface NotificationSender {
    void update (User user, String message);
}

//subject