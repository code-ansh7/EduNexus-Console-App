package services;

import models.User;

public class AdminDashboard {

    public static void show(User user) {

        System.out.println();
        System.out.println("========================================");
        System.out.println("          ADMIN DASHBOARD");
        System.out.println("========================================");
        System.out.println();

        System.out.println("Welcome, " + user.getName());

        System.out.println();
        System.out.println("Admin Features Coming Soon...");

    }

}