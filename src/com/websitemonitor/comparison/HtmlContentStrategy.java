package com.websitemonitor.comparison;

import com.websitemonitor.model.entity.WebsiteSubscription;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HtmlContentStrategy implements ComparisonStrategy {

    @Override
    public boolean hasChanged(WebsiteSubscription subscription, String newContent) {
        String currentHash = hash(newContent);
        String lastHash = subscription.getLastContentHash();

        if (lastHash == null) {
            subscription.setLastContentHash(currentHash);
            return false;
        }

        if (!currentHash.equals(lastHash)) {
            subscription.setLastContentHash(currentHash);
            return true;
        }

        return false;

    }
    @Override
    public boolean isEqual(String content1, String content2) {
        if (content1 == null || content2 == null) return false;
        return content1.equals(content2);
    }

    private String hash(String content) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = digest.digest(content.getBytes());
            StringBuilder hex = new StringBuilder();
            for (byte b : bytes) {
                String h = Integer.toHexString(0xff & b);
                if (h.length() == 1) hex.append('0');
                hex.append(h);
            }
            return hex.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hashing error", e);
        }
    }
}
