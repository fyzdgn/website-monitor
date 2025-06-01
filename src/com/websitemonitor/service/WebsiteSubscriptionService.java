package com.websitemonitor.service;

import com.websitemonitor.model.entity.User;
import com.websitemonitor.model.entity.WebsiteSubscription;
import com.websitemonitor.model.enums.NotificationFrequency;
import com.websitemonitor.repository.WebsiteSubscriptionRepository;

import java.util.Optional;

public class WebsiteSubscriptionService {
    private final WebsiteSubscriptionRepository subscriptionRepository;

    public WebsiteSubscriptionService(WebsiteSubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public WebsiteSubscription createSubscription(String websiteUrl, User user,
                                                  String frequency) {
        WebsiteSubscription subscription = new WebsiteSubscription(
                generateSubscriptionId(), websiteUrl,
                NotificationFrequency.valueOf(frequency.toUpperCase()), user);
        subscriptionRepository.save(subscription);
        user.addSubscription(subscription);
        return subscription;
    }

    public Optional<WebsiteSubscription> getSubscription(String subscriptionId) {
        return subscriptionRepository.findById(subscriptionId);
    }

    public void updateSubscriptionFrequency(String subscriptionId, String newFrequency) {
        subscriptionRepository.findById(subscriptionId).ifPresent(subscription -> {
            subscription.setFrequency(NotificationFrequency.valueOf(newFrequency.toUpperCase()));
            subscriptionRepository.save(subscription);
        });
    }

    public void cancelSubscription(String subscriptionId, User user) {
        subscriptionRepository.findById(subscriptionId).ifPresent(subscription -> {
            user.removeSubscription(subscription);
            subscriptionRepository.delete(subscriptionId);
        });
    }

    private String generateSubscriptionId() {
        return "sub-" + System.currentTimeMillis();
    }
}