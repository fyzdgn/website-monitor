package com.websitemonitor.notification;

import com.websitemonitor.model.entity.WebsiteSubscription;

public interface NotificationObserver {
    void update(WebsiteSubscription subscription, String message);
}
