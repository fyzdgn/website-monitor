package com.websitemonitor.comparison;

import com.websitemonitor.model.entity.WebsiteSubscription;

public interface ComparisonStrategy {
    boolean hasChanged(WebsiteSubscription subscription, String newContent);
    boolean isEqual(String content1, String content2);

}
