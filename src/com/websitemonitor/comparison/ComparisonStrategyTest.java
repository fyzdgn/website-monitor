package com.websitemonitor.comparison;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ComparisonStrategyTest {

    @Test
    void testContentSize_equalLength() {
        ComparisonStrategy strategy = new ContentSizeStrategy();
        assertTrue(strategy.isEqual("abc", "xyz")); // same length
    }

    @Test
    void testContentSize_differentLength() {
        ComparisonStrategy strategy = new ContentSizeStrategy();
        assertFalse(strategy.isEqual("abc", "abcd")); // different length
    }

    @Test
    void testHtmlContent_equalHtml() {
        ComparisonStrategy strategy = new HtmlContentStrategy();
        assertTrue(strategy.isEqual("<div>Hello</div>", "<div>Hello</div>"));
    }

    @Test
    void testHtmlContent_differentHtml() {
        ComparisonStrategy strategy = new HtmlContentStrategy();
        assertFalse(strategy.isEqual("<div>Hello</div>", "<p>Hello</p>"));
    }

    @Test
    void testTextContent_sameTextDifferentTags() {
        ComparisonStrategy strategy = new TextContentStrategy();
        assertTrue(strategy.isEqual("<p>Hello</p>", "<div>Hello</div>"));
    }

    @Test
    void testTextContent_differentVisibleText() {
        ComparisonStrategy strategy = new TextContentStrategy();
        assertFalse(strategy.isEqual("<p>Hello</p>", "<p>Bye</p>"));
    }

    @Test
    void testSimpleComparison_exactMatch() {
        ComparisonStrategy strategy = new SimpleComparisonStrategy();
        assertTrue(strategy.isEqual("Tetris", "Tetris"));
    }

    @Test
    void testSimpleComparison_mismatch() {
        ComparisonStrategy strategy = new SimpleComparisonStrategy();
        assertFalse(strategy.isEqual("Tetris", "Puzzle"));
    }

    @Test
    void testNullInputs_allStrategiesReturnFalse() {
        ComparisonStrategy html = new HtmlContentStrategy();
        ComparisonStrategy text = new TextContentStrategy();
        ComparisonStrategy size = new ContentSizeStrategy();
        ComparisonStrategy simple = new SimpleComparisonStrategy();

        assertFalse(html.isEqual(null, "abc"));
        assertFalse(text.isEqual(null, "abc"));
        assertFalse(size.isEqual(null, "abc"));
        assertFalse(simple.isEqual(null, "abc"));
    }
}


