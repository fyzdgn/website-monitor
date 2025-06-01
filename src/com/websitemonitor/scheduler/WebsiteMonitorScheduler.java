package com.websitemonitor.scheduler;

import com.websitemonitor.model.entity.WebsiteSubscription;
import com.websitemonitor.model.enums.NotificationChannel;
import com.websitemonitor.notification.NotificationFactory;
import com.websitemonitor.notification.NotificationSender;
import com.websitemonitor.repository.WebsiteSubscriptionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WebsiteMonitorScheduler {
    private final WebsiteSubscriptionRepository subscriptionRepository;
    private final ScheduledExecutorService scheduler;
    private final WebsiteChecker websiteChecker;

    public WebsiteMonitorScheduler(WebsiteSubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.scheduler = Executors.newScheduledThreadPool(1);
        this.websiteChecker = new WebsiteChecker();
    }

    public void startMonitoring() {
        scheduler.scheduleAtFixedRate(this::checkAllSubscriptions, 0, 1, TimeUnit.HOURS);
    }

    public void checkAllSubscriptions() {
        List<WebsiteSubscription> subscriptions = subscriptionRepository.findAll();

        subscriptions.forEach(subscription -> {
            if (shouldCheckNow(subscription)) {
                boolean hasUpdate = websiteChecker.checkForUpdates(subscription);
                if (hasUpdate) {
                    notifyUser(subscription);
                }
                subscription.setLastChecked(LocalDateTime.now());
                subscriptionRepository.save(subscription);
            }
        });
    }

    private boolean shouldCheckNow(WebsiteSubscription subscription) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime lastChecked = subscription.getLastChecked();

        switch (subscription.getFrequency()) {
            case HOURLY:
                return lastChecked.isBefore(now.minusHours(1));
            case DAILY:
                return lastChecked.isBefore(now.minusDays(1));
            case WEEKLY:
                return lastChecked.isBefore(now.minusWeeks(1));
            case MONTHLY:
                return lastChecked.isBefore(now.minusMonths(1));
            default:
                return false;
        }
    }

    private void notifyUser(WebsiteSubscription subscription) {
        String message = "The website " + subscription.getWebsiteUrl() + " has been updated!";
        NotificationSender sender = NotificationFactory.getSender(NotificationChannel.EMAIL);
        sender.sendNotification(subscription.getUser(), message);
    }

    public void shutdown() {
        scheduler.shutdown();
    }
}