package com.websitemonitor.notification;

import com.websitemonitor.model.entity.WebsiteSubscription;

/**
 * Observer interface for receiving website update notifications.
 * Implementing classes (e.g. User) will be notified by WebsiteUpdateNotifier.
 */
public interface NotificationObserver {
    /**
     * Called when the observed website subscription has an update.
     *
     * @param subscription the updated website subscription
     * @param message      the update message
     */
    void update(WebsiteSubscription subscription, String message);
}
