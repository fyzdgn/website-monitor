package com.websitemonitor.scheduler;

import com.websitemonitor.model.entity.WebsiteSubscription;
import com.websitemonitor.notification.EmailNotificationSender;
import com.websitemonitor.notification.WebsiteUpdateNotifier;
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

        // Observer pattern için notifier oluşturuluyor ve observer ekleniyor
        WebsiteUpdateNotifier notifier = new WebsiteUpdateNotifier();
        notifier.addObserver(new EmailNotificationSender());

        this.websiteChecker = new WebsiteChecker(notifier);
    }

    public void startMonitoring() {
        scheduler.scheduleAtFixedRate(this::checkAllSubscriptions, 0, 5, TimeUnit.SECONDS);
    }

    public void checkAllSubscriptionsImmediately() {
        this.checkAllSubscriptions();
    }

    private void checkAllSubscriptions() {
        List<WebsiteSubscription> subscriptions = subscriptionRepository.findAll();

        LocalDateTime now = LocalDateTime.now();

        System.out.println("Starting subscription checks at: " + now);

        subscriptions.forEach(subscription -> {
            System.out.println("Checking subscription: " + subscription.getSubscriptionId() + " URL: " + subscription.getWebsiteUrl());

            if (shouldCheckNow(subscription, now)) {
                boolean updated = websiteChecker.checkForUpdates(subscription);

                if (updated) {
                    System.out.println("Website update detected for: " + subscription.getWebsiteUrl());
                } else {
                    System.out.println("No update detected for: " + subscription.getWebsiteUrl());
                }

                subscription.setLastChecked(now);
                subscriptionRepository.save(subscription);
            } else {
                System.out.println("Not time to check subscription: " + subscription.getSubscriptionId());
            }
        });
    }


    private boolean shouldCheckNow(WebsiteSubscription subscription, LocalDateTime now) {
        LocalDateTime lastChecked = subscription.getLastChecked();

        if (lastChecked == null) {
            return true;
        }

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

    public void shutdown() {
        scheduler.shutdown();
    }
}
