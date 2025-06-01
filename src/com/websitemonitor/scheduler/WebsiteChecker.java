package com.websitemonitor.scheduler;

import com.websitemonitor.model.entity.WebsiteSubscription;

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

    public WebsiteChecker() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public boolean checkForUpdates(WebsiteSubscription subscription) {
        try {
            String currentContent = fetchWebsiteContent(subscription.getWebsiteUrl());
            String currentHash = calculateHash(currentContent);

            if (subscription.getLastContentHash() == null) {
                // First check
                subscription.setLastContentHash(currentHash);
                return false;
            }

            if (!currentHash.equals(subscription.getLastContentHash())) {
                subscription.setLastContentHash(currentHash);
                subscription.setLastUpdated(LocalDateTime.now());
                return true;
            }

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