package com.websitemonitor.comparison;

import com.websitemonitor.model.entity.WebsiteSubscription;

public class TextContentStrategy implements ComparisonStrategy {
    /**
     A strategy that compares the plain text extracted from the website content.
     It ignores HTML tags and compares only visible text.
     Returns true if the plain text content has changed.
     */
    @Override
    public boolean hasChanged(WebsiteSubscription subscription, String newContent) {
        String textOnly = newContent.replaceAll("<[^>]*>", "").replaceAll("\\s+", " ").trim();
        String currentHash = String.valueOf(textOnly.hashCode());
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
}

