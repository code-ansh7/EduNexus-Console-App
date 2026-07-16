package services;

import java.util.Scanner;
import models.User;
import utils.InputHelper;

public class StudentDashboard {

    public static void show(User user) {

        while (true) {

            System.out.println("\n=================================");
            System.out.println("      STUDENT DASHBOARD");
            System.out.println("=================================");

            System.out.println("Welcome : " + user.getName());

            System.out.println("\n1. View Profile");
            System.out.println("2. View Homework");
            System.out.println("3. School Notices");
            System.out.println("4. Feelings Corner");
            System.out.println("5. Logout");

            System.out.print("\nEnter Choice : ");

            String choice = InputHelper.scanner.nextLine();

            switch (choice) {

                case "1":
                    System.out.println("\nProfile Feature Coming Soon...");
                    break;

                case "2":
                    System.out.println("\nHomework Feature Coming Soon...");
                    break;

                case "3":
                    System.out.println("\nNotice Feature Coming Soon...");
                    break;

                case "4":
                    System.out.println("\nFeelings Feature Coming Soon...");
                    break;

                case "5":
                    return;

                default:
                    System.out.println("\nInvalid Choice.");
            }

        }

    }

}