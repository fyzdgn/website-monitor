package com.websitemonitor.comparison;

import com.websitemonitor.model.entity.WebsiteSubscription;

public class ContentSizeStrategy implements ComparisonStrategy {
    /**
     A strategy that compares the size (length) of the website content.
     It considers the content changed if the length of the new content differs from the previous one.
     This method is simple and fast but may miss subtle content changes.
     */
    @Override
    public boolean hasChanged(WebsiteSubscription subscription, String newContent) {
        String lastHash = subscription.getLastContentHash();
        String currentSize = String.valueOf(newContent.length());

        if (lastHash == null) {
            subscription.setLastContentHash(currentSize);
            return false;
        }

        if (!currentSize.equals(lastHash)) {
            subscription.setLastContentHash(currentSize);
            return true;
        }

        return false;
    }
}
