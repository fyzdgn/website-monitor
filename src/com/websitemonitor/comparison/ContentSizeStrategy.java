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

    @Override
    public boolean isEqual(String content1, String content2) {
        if (content1 == null || content2 == null) return false;
        return content1.length() == content2.length();
    }
}
