package services;

import models.User;

public class TeacherDashboard {

    public static void show(User user) {

        System.out.println();
        System.out.println("========================================");
        System.out.println("         TEACHER DASHBOARD");
        System.out.println("========================================");
        System.out.println();

        System.out.println("Welcome, " + user.getName());

        System.out.println();
        System.out.println("Teacher Features Coming Soon...");

    }
}