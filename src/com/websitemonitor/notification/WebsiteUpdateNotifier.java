package com.websitemonitor.notification;

import com.websitemonitor.model.entity.WebsiteSubscription;

import java.util.ArrayList;
import java.util.List;

/**
 * Acts as the Subject in the Observer pattern.
 * Maintains a list of observers (NotificationObservers) and notifies them on update.
 */
public class WebsiteUpdateNotifier {

    private final List<NotificationObserver> observers = new ArrayList<>();

    /**
     * Attaches an observer to the notifier.
     *
     * @param observer the observer to be added
     */
    public void addObserver(NotificationObserver observer) {
        observers.add(observer);
    }

    /**
     * Detaches an observer from the notifier.
     *
     * @param observer the observer to be removed
     */
    public void removeObserver(NotificationObserver observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all observers with an update.
     *
     * @param subscription the website subscription that was updated
     * @param message      the update message
     */
    public void notifyObservers(WebsiteSubscription subscription, String message) {
        for (NotificationObserver observer : observers) {
            observer.update(subscription, message);
        }
    }
}
