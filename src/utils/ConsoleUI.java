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

}