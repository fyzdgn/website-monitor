package com.websitemonitor.repository;

import com.websitemonitor.model.entity.WebsiteSubscription;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class WebsiteSubscriptionRepository {
    private final Map<String, WebsiteSubscription> subscriptions = new HashMap<>();

    public void save(WebsiteSubscription subscription) {
        subscriptions.put(subscription.getSubscriptionId(), subscription);
    }

    public Optional<WebsiteSubscription> findById(String subscriptionId) {
        return Optional.ofNullable(subscriptions.get(subscriptionId));
    }

    public void delete(String subscriptionId) {
        subscriptions.remove(subscriptionId);
    }

   //FindAll method
    public List<WebsiteSubscription> findAll() {
        return new ArrayList<>(subscriptions.values());
    }
}