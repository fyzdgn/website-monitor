package com.websitemonitor.model.entity;

import com.websitemonitor.comparison.ComparisonStrategy;
import com.websitemonitor.model.enums.NotificationFrequency;

import java.time.LocalDateTime;

public class WebsiteSubscription {
    private String subscriptionId;
    private String websiteUrl;
    private LocalDateTime lastChecked;
    private LocalDateTime lastUpdated;
    private String lastContentHash;
    private String lastContent;
    private NotificationFrequency frequency;
    private User user;

    private ComparisonStrategy comparisonStrategy;

    public WebsiteSubscription(String subscriptionId, String websiteUrl,
                               NotificationFrequency frequency, User user,
                               ComparisonStrategy comparisonStrategy) {
        this.subscriptionId = subscriptionId;
        this.websiteUrl = websiteUrl;
        this.frequency = frequency;
        this.user = user;
        this.comparisonStrategy = comparisonStrategy;
        this.lastChecked = null;
    }

    // Getter and Setter
    public String getSubscriptionId() { return subscriptionId; }
    public String getWebsiteUrl() { return websiteUrl; }
    public LocalDateTime getLastChecked() { return lastChecked; }
    public LocalDateTime getLastUpdated() { return lastUpdated; }
    public String getLastContentHash() { return lastContentHash; }
    public String getLastContent() { return lastContent; }
    public NotificationFrequency getFrequency() { return frequency; }
    public User getUser() { return user; }
    public ComparisonStrategy getComparisonStrategy() { return comparisonStrategy; }

    public void setLastChecked(LocalDateTime lastChecked) { this.lastChecked = lastChecked; }
    public void setLastUpdated(LocalDateTime lastUpdated) { this.lastUpdated = lastUpdated; }
    public void setLastContentHash(String lastContentHash) { this.lastContentHash = lastContentHash; }
    public void setLastContent(String lastContent) { this.lastContent = lastContent; }
    public void setFrequency(NotificationFrequency frequency) { this.frequency = frequency; }
    public void setComparisonStrategy(ComparisonStrategy comparisonStrategy) { this.comparisonStrategy = comparisonStrategy; }
}
