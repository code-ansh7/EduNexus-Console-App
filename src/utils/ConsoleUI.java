package utils;
import java.util.Scanner;
import auth.LoginManager;

public class ConsoleUI {

    public static void showSplashScreen() {

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

        InputHelper.scanner.nextLine();

    }

    public static void showWelcomeScreen() {
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
            int choice = InputHelper.scanner.nextInt();
            InputHelper.scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println();
                    LoginManager.showLoginScreen();
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