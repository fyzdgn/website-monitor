package com.websitemonitor.comparison;

import com.websitemonitor.model.entity.WebsiteSubscription;

public class SimpleComparisonStrategy implements ComparisonStrategy {

    @Override
    public boolean hasChanged(WebsiteSubscription subscription, String newContent) {
        String oldContent = subscription.getLastContent();
        return oldContent == null || !oldContent.equals(newContent);
    }

    @Override
    public boolean isEqual(String content1, String content2) {
        if (content1 == null || content2 == null) return false;
        return content1.equals(content2);
    }

}

