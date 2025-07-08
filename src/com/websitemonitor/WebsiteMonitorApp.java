package com.websitemonitor;

import com.websitemonitor.controller.SubscriptionController;
import com.websitemonitor.controller.UserController;
import com.websitemonitor.model.entity.User;
import com.websitemonitor.model.entity.WebsiteSubscription;
import com.websitemonitor.repository.UserRepository;
import com.websitemonitor.repository.WebsiteSubscriptionRepository;
import com.websitemonitor.scheduler.WebsiteMonitorScheduler;
import com.websitemonitor.service.UserService;
import com.websitemonitor.service.WebsiteSubscriptionService;

/**
 * Interface defining the strategy for comparing website content.
 * The method 'hasChanged' checks if the website content has been updated.
 * This allows different comparison algorithms to be implemented and swapped easily.
 */
public class WebsiteMonitorApp {
    public static void main(String[] args) {
        // Initialize repositories
        UserRepository userRepository = new UserRepository();
        WebsiteSubscriptionRepository subscriptionRepository = new WebsiteSubscriptionRepository();

        // Initialize services
        UserService userService = new UserService(userRepository);
        WebsiteSubscriptionService subscriptionService = new WebsiteSubscriptionService(subscriptionRepository);

        // Initialize controllers
        UserController userController = new UserController(userService);
        SubscriptionController subscriptionController = new SubscriptionController(subscriptionService);

        // Initialize scheduler
        WebsiteMonitorScheduler scheduler = new WebsiteMonitorScheduler(subscriptionRepository);
        scheduler.startMonitoring();


        // Example usage
        User user = userController.registerUser("feyza", "feyza@gmail.com");
        WebsiteSubscription subscription = subscriptionController.createSubscription(
                user, "https://google.com", "daily");

        System.out.println("User registered and subscription created!");

        // In a real application, we would have a proper shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(scheduler::shutdown));

    }
}