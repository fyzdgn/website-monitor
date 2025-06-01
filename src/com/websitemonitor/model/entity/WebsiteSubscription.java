package com.websitemonitor.model.entity;

import com.websitemonitor.model.enums.NotificationFrequency;

import java.time.LocalDateTime;

public class WebsiteSubscription {
    private String subscriptionId;
    private String websiteUrl;
    private LocalDateTime lastChecked;
    private LocalDateTime lastUpdated;
    private String lastContentHash;
    private NotificationFrequency frequency;
    private User user;

    public WebsiteSubscription(String subscriptionId, String websiteUrl,
                               NotificationFrequency frequency, User user) {
        this.subscriptionId = subscriptionId;
        this.websiteUrl = websiteUrl;
        this.frequency = frequency;
        this.user = user;
        this.lastChecked = LocalDateTime.now();
    }

    // Getters and setters
    public String getSubscriptionId() { return subscriptionId; }
    public String getWebsiteUrl() { return websiteUrl; }
    public LocalDateTime getLastChecked() { return lastChecked; }
    public LocalDateTime getLastUpdated() { return lastUpdated; }
    public String getLastContentHash() { return lastContentHash; }
    public NotificationFrequency getFrequency() { return frequency; }
    public User getUser() { return user; }

    public void setLastChecked(LocalDateTime lastChecked) { this.lastChecked = lastChecked; }
    public void setLastUpdated(LocalDateTime lastUpdated) { this.lastUpdated = lastUpdated; }
    public void setLastContentHash(String lastContentHash) { this.lastContentHash = lastContentHash; }
    public void setFrequency(NotificationFrequency frequency) { this.frequency = frequency; }
}