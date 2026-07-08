package auth;

import utils.InputHelper;

public class LoginManager {

    public static void showLoginScreen() {

        System.out.println();
        System.out.println("======================================");
        System.out.println("              LOGIN                   ");
        System.out.println("======================================");
        System.out.println();

        System.out.print("User ID : ");
        String userId = InputHelper.scanner.nextLine();

        System.out.print("Password : ");
        String password = InputHelper.scanner.nextLine();

        System.out.println();
        System.out.println("Authenticating...");
        System.out.println();

        System.out.println("Feature Under Development");

    }

}