package utils;

public class ConsoleUI {

    public static void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static void showSplashScreen() {
        clearScreen();
        System.out.println("\n");
        System.out.println("  ╔══════════════════════════════════════════════════╗");
        System.out.println("  ║                                                  ║");
        System.out.println("  ║          _____    _   _  _____  _   _            ║");
        System.out.println("  ║         |  ___|  | | | ||_   _|| \\ | |          ║");
        System.out.println("  ║         | |__    | | | |  | |  |  \\| |          ║");
        System.out.println("  ║         |  __|   | | | |  | |  | . ` |           ║");
        System.out.println("  ║         | |___   | |_| | _| |_ | |\\  |          ║");
        System.out.println("  ║         |_____|   \\___/ |_____||_| \\_|         ║");
        System.out.println("  ║                                                  ║");
        System.out.println("  ║        Smart School Management System            ║");
        System.out.println("  ║                                                  ║");
        System.out.println("  ╚══════════════════════════════════════════════════╝");
        System.out.println("\n");
        System.out.println("              Loading... Please wait                  ");
        try { Thread.sleep(1200); } catch (InterruptedException e) { }
    }

    public static void showWelcomeScreen() {
        clearScreen();
        System.out.println("\n");
        System.out.println("  ╔══════════════════════════════════════════════════╗");
        System.out.println("  ║                                                  ║");
        System.out.println("  ║           Welcome to EduNexus 🎓                 ║");
        System.out.println("  ║                                                  ║");
        System.out.println("  ║     Your Smart School Management System          ║");
        System.out.println("  ║                                                  ║");
        System.out.println("  ╚══════════════════════════════════════════════════╝");
        System.out.println("\n");
        System.out.println("  ┌──────────────────────────────────────────────────┐");
        System.out.println("  │  1. Login                                        │");
        System.out.println("  │  2. Exit                                         │");
        System.out.println("  └──────────────────────────────────────────────────┘");
        System.out.println();
    }

    public static void showExitMessage() {
        clearScreen();
        System.out.println("\n");
        System.out.println("  ╔══════════════════════════════════════════════════╗");
        System.out.println("  ║                                                  ║");
        System.out.println("  ║          Thank you for using EduNexus!           ║");
        System.out.println("  ║              Have a great day! 👋                ║");
        System.out.println("  ║                                                  ║");
        System.out.println("  ╚══════════════════════════════════════════════════╝");
        System.out.println("\n");
    }

    public static void showLoginHeader() {
        clearScreen();
        System.out.println("\n");
        System.out.println("  ╔══════════════════════════════════════════════════╗");
        System.out.println("  ║                   🔐 LOGIN                       ║");
        System.out.println("  ╚══════════════════════════════════════════════════╝");
        System.out.println();
    }

    public static void showStudentDashboardHeader(String name) {
        clearScreen();
        System.out.println("\n");
        System.out.println("  ╔══════════════════════════════════════════════════╗");
        System.out.println("  ║        🎓 STUDENT DASHBOARD                      ║");
        System.out.println("  ║        Welcome, " + padRight(name, 33) + " ║");
        System.out.println("  ╚══════════════════════════════════════════════════╝");
        System.out.println();
    }

    public static void showTeacherDashboardHeader(String name) {
        clearScreen();
        System.out.println("\n");
        System.out.println("  ╔══════════════════════════════════════════════════╗");
        System.out.println("  ║        👨‍🏫 TEACHER DASHBOARD                    ║");
        System.out.println("  ║        Welcome, " + padRight(name, 33) + " ║");
        System.out.println("  ╚══════════════════════════════════════════════════╝");
        System.out.println();
    }

    public static void showAdminDashboardHeader(String name) {
        clearScreen();
        System.out.println("\n");
        System.out.println("  ╔══════════════════════════════════════════════════╗");
        System.out.println("  ║        ⚙️  ADMIN DASHBOARD                       ║");
        System.out.println("  ║        Welcome, " + padRight(name, 33) + " ║");
        System.out.println("  ╚══════════════════════════════════════════════════╝");
        System.out.println();
    }

    public static void printSuccess(String message) {
        System.out.println("\n  ✅ " + message);
    }

    public static void printError(String message) {
        System.out.println("\n  ❌ " + message);
    }

    public static void printInfo(String message) {
        System.out.println("\n  ℹ️  " + message);
    }

    public static void printWarning(String message) {
        System.out.println("\n  ⚠️  " + message);
    }

    public static void printHeader(String title) {
        System.out.println("\n  ┌──────────────────────────────────────────────────┐");
        System.out.println("  │  " + padRight(title, 48) + "│");
        System.out.println("  └──────────────────────────────────────────────────┘");
        System.out.println();
    }

    public static void printBox(String content) {
        System.out.println("  │ " + padRight(content, 48) + "│");
    }

    public static void pressEnterToContinue() {
        System.out.println("\n  Press Enter to continue...");
        new java.util.Scanner(System.in).nextLine();
    }

    private static String padRight(String s, int n) {
        if (s == null) s = "";
        if (s.length() >= n) return s.substring(0, n);
        return s + String.format("%" + (n - s.length()) + "s", "");
    }
}