package app;

import auth.LoginManager;
import auth.SessionManager;
import navigation.DashboardRouter;
import utils.ConsoleUI;

/**
 * Main class - Entry point of EduNexus application
 * This is where the program starts execution
 */
public class Main {
    
    /**
     * Main method - JVM calls this method to start the program
     * @param args Command line arguments (not used in this app)
     */
    public static void main(String[] args) {
        // Create scanner object to read user input
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        
        // Keep the application running until user chooses to exit
        boolean keepRunning = true;
        
        while (keepRunning) {
            // Show splash screen with animation
            ConsoleUI.showSplashScreen();
            
            // Show welcome screen
            ConsoleUI.showWelcomeScreen();
            
            // Start login process
            LoginManager loginManager = new LoginManager();
            boolean loginSuccess = loginManager.login(scanner);
            
            // If login is successful, route to appropriate dashboard
            if (loginSuccess) {
                // Get the logged-in user's role
                String userRole = SessionManager.getCurrentUserRole();
                String userId = SessionManager.getCurrentUserId();
                
                // Route to the correct dashboard based on role
                DashboardRouter router = new DashboardRouter();
                router.routeToDashboard(userRole, userId, scanner);
                
                // After logout, session is cleared, loop continues to show welcome screen again
            } else {
                // Login failed, ask if user wants to try again or exit
                ConsoleUI.printError("Login failed!");
                System.out.println("\n1. Try Again");
                System.out.println("2. Exit");
                System.out.print("\nEnter your choice: ");
                
                String choice = scanner.nextLine().trim();
                
                if (choice.equals("2")) {
                    keepRunning = false;
                    ConsoleUI.showExitMessage();
                }
                // If choice is 1, loop continues and shows login again
            }
        }
        
        // Close scanner to prevent resource leak
        scanner.close();
    }
}