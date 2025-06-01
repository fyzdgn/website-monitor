package com.websitemonitor.comparison;

import com.websitemonitor.model.entity.WebsiteSubscription;

public class ContentSizeStrategy implements ComparisonStrategy {

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
