package com.websitemonitor.comparison;

public class ComparisonStrategyTest {
    public static void main(String[] args) {

        System.out.println("==== ContentSizeStrategy ====");
        ContentSizeStrategy sizeStrategy = new ContentSizeStrategy();
        System.out.println("Same size: " + sizeStrategy.isEqual("abc", "xyz"));         // true
        System.out.println("Different size: " + sizeStrategy.isEqual("abc", "xy"));     // false
        System.out.println("Empty strings: " + sizeStrategy.isEqual("", ""));           // true
        System.out.println("Null input: " + sizeStrategy.isEqual(null, "abc"));         // false

        System.out.println("\n==== HtmlContentStrategy ====");
        HtmlContentStrategy htmlStrategy = new HtmlContentStrategy();
        System.out.println("Same HTML: " + htmlStrategy.isEqual("<p>Hi</p>", "<p>Hi</p>"));    // true
        System.out.println("Different HTML: " + htmlStrategy.isEqual("<p>Hi</p>", "<div>Hi</div>")); // false
        System.out.println("HTML vs text: " + htmlStrategy.isEqual("<p>Hi</p>", "Hi"));        // false
        System.out.println("Null input: " + htmlStrategy.isEqual(null, "<p>Hi</p>"));          // false

        System.out.println("\n==== TextContentStrategy ====");
        TextContentStrategy textStrategy = new TextContentStrategy();
        System.out.println("Same text, different HTML: " + textStrategy.isEqual("<p>Hello</p>", "<div>Hello</div>")); // true
        System.out.println("Different text: " + textStrategy.isEqual("<p>Hello</p>", "<p>Bye</p>"));                  // false
        System.out.println("Whitespace: " + textStrategy.isEqual("<p> </p>", "<div></div>"));                         // true
        System.out.println("Null input: " + textStrategy.isEqual(null, "<p>Hi</p>"));                                 // false

        System.out.println("\n==== SimpleComparisonStrategy ====");
        SimpleComparisonStrategy simpleStrategy = new SimpleComparisonStrategy();
        System.out.println("Exact same content: " + simpleStrategy.isEqual("hello", "hello"));       // true
        System.out.println("Different content: " + simpleStrategy.isEqual("hello", "world"));        // false
        System.out.println("One null input: " + simpleStrategy.isEqual(null, "hello"));              // false
        System.out.println("Both empty: " + simpleStrategy.isEqual("", ""));
    }
}
