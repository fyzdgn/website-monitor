package com.websitemonitor.comparison;

import com.websitemonitor.model.entity.WebsiteSubscription;

public class TextContentStrategy implements ComparisonStrategy {

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

    @Override
    public boolean isEqual(String content1, String content2) {
        if (content1 == null || content2 == null) return false;

        String text1 = content1.replaceAll("<[^>]*>", "").replaceAll("\\s+", " ").trim();
        String text2 = content2.replaceAll("<[^>]*>", "").replaceAll("\\s+", " ").trim();

        return text1.equals(text2);
    }
}
