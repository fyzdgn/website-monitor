package com.websitemonitor.comparison;

import com.websitemonitor.model.entity.WebsiteSubscription;
/**
 * A simple implementation of ComparisonStrategy that compares the old content
 * with the new content as plain text.
 * Returns true if content has changed, otherwise false.
 */
public class SimpleComparisonStrategy implements ComparisonStrategy {
    @Override
    public boolean hasChanged(WebsiteSubscription subscription, String newContent) {
        String oldContent = subscription.getLastContent();
        return oldContent == null || !oldContent.equals(newContent);
    }
}

