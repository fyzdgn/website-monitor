package com.websitemonitor.scheduler;

import com.websitemonitor.model.entity.WebsiteSubscription;
import com.websitemonitor.notification.WebsiteUpdateNotifier;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

public class WebsiteChecker {
    private final HttpClient httpClient;
    private final WebsiteUpdateNotifier notifier;

    public WebsiteChecker(WebsiteUpdateNotifier notifier) {
        this.httpClient = HttpClient.newHttpClient();
        this.notifier = notifier;
    }

    public boolean checkForUpdates(WebsiteSubscription subscription) {
        try {
            String currentContent = fetchWebsiteContent(subscription.getWebsiteUrl());
            String currentHash = calculateHash(currentContent);

            System.out.println("Checking website: " + subscription.getWebsiteUrl());
            System.out.println("Current hash: " + currentHash);
            System.out.println("Last hash: " + subscription.getLastContentHash());

            if (subscription.getLastContentHash() == null) {
                subscription.setLastContentHash(currentHash);
                System.out.println("Initial hash set, no notification sent.");
                return false;
            }

            if (!currentHash.equals(subscription.getLastContentHash())) {
                subscription.setLastContentHash(currentHash);
                subscription.setLastUpdated(LocalDateTime.now());

                System.out.println("Change detected! Notifying observers...");
                notifier.notifyObservers(subscription,
                        "The website " + subscription.getWebsiteUrl() + " has been updated!");

                return true;
            }

            System.out.println("No changes detected.");
            return false;
        } catch (Exception e) {
            System.err.println("Error checking website: " + e.getMessage());
            return false;
        }
    }


    private String fetchWebsiteContent(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    private String calculateHash(String content) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(content.getBytes());

        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();
    }
}
