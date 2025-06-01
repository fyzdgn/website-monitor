package com.websitemonitor.notification;

import com.websitemonitor.model.entity.User;
import com.websitemonitor.model.entity.WebsiteSubscription;


import java.util.ArrayList;
import java.util.List;

public class WebsiteUpdateNotifier {
    private final List<NotificationObserver> observers = new ArrayList<>();

    public void addObserver(NotificationObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(NotificationObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(WebsiteSubscription subscription, String message) {
        for (NotificationObserver observer : observers) {
            observer.update(subscription, message);
        }
    }
}
