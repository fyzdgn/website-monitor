package com.websitemonitor.comparison;

import com.websitemonitor.model.entity.WebsiteSubscription;

public class SimpleComparisonStrategy implements ComparisonStrategy {
    @Override
    public boolean hasChanged(WebsiteSubscription subscription, String newContent) {
        String oldContent = subscription.getLastContent();
        return oldContent == null || !oldContent.equals(newContent);
    }
}

