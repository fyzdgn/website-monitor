package com.websitemonitor.service;

import com.websitemonitor.model.entity.User;
import com.websitemonitor.model.entity.WebsiteSubscription;
import com.websitemonitor.model.enums.NotificationFrequency;
import com.websitemonitor.repository.WebsiteSubscriptionRepository;
import com.websitemonitor.comparison.ComparisonStrategy;
import com.websitemonitor.comparison.SimpleComparisonStrategy;

import java.util.Optional;
/**
 * Service class responsible for managing website subscriptions.
 * When creating a subscription, it accepts a ComparisonStrategy instance,
 * allowing each subscription to define its own way of detecting content changes.
 */
public class WebsiteSubscriptionService {
    private final WebsiteSubscriptionRepository subscriptionRepository;
    private final ComparisonStrategy defaultStrategy = new SimpleComparisonStrategy();

    public WebsiteSubscriptionService(WebsiteSubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }


    public WebsiteSubscription createSubscription(User user, String url, String frequency) {
        return createSubscription(user, url, frequency, defaultStrategy);
    }


    public WebsiteSubscription createSubscription(User user, String url, String frequency, ComparisonStrategy strategy) {
        WebsiteSubscription subscription = new WebsiteSubscription(
                generateSubscriptionId(),
                url,
                NotificationFrequency.valueOf(frequency.toUpperCase()),
                user,
                strategy
        );
        subscriptionRepository.save(subscription);
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
