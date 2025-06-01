package com.websitemonitor.controller;

import com.websitemonitor.model.entity.User;
import com.websitemonitor.model.entity.WebsiteSubscription;
import com.websitemonitor.service.WebsiteSubscriptionService;

public class SubscriptionController {
    private final WebsiteSubscriptionService subscriptionService;

    public SubscriptionController(WebsiteSubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    public WebsiteSubscription createSubscription(User user, String websiteUrl, String frequency) {
        return subscriptionService.createSubscription(websiteUrl, user, frequency);
    }

    public void updateSubscriptionFrequency(String subscriptionId, String newFrequency) {
        subscriptionService.updateSubscriptionFrequency(subscriptionId, newFrequency);
    }

    public void cancelSubscription(User user, String subscriptionId) {
        subscriptionService.cancelSubscription(subscriptionId, user);
    }
}