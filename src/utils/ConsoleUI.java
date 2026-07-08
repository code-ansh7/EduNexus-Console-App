package utils;

import java.util.Scanner;

public class ConsoleUI {

    public static void showSplashScreen() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("==================================================");
        System.out.println("                  EDUNEXUS");
        System.out.println("==================================================");
        System.out.println("      Smart School Management Platform            ");
        System.out.println();
        System.out.println("             Console Prototype v0.2               ");
        System.out.println();
        System.out.println(" Developed By : Ansh Rastogi                      ");
        System.out.println("==================================================");
        System.out.println();
        System.out.println("Press ENTER to continue...");

        scanner.nextLine();

    }

    public static void showWelcomeScreen() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.println("=================================================");
            System.out.println("               WELCOME TO EDUNEXUS");
            System.out.println("=================================================");
            System.out.println();
            System.out.println("1. Login");
            System.out.println("0. Exit");
            System.out.println();
            System.out.print("Enter Choice : ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println();
                    System.out.println("Opening Login Screen...");
                    System.out.println("(Login Feature Coming Soon)");
                    return;

                case 0:
                    System.out.println();
                    System.out.println("Thank you for using EduNexus.");
                    System.exit(0);

                default:
                    System.out.println();
                    System.out.println("Invalid Choice! Please try again.");
            }
        }
    }
}