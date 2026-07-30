package auth;

import models.User;

public class SessionManager {

    private static User currentUser = null;
    private static boolean isLoggedIn = false;

    public static void setCurrentUser(User user) {
        currentUser = user;
        isLoggedIn = (user != null);
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static String getCurrentUserId() {
        return currentUser != null ? currentUser.getId() : null;
    }

    public static String getCurrentUserName() {
        return currentUser != null ? currentUser.getName() : null;
    }

    public static String getCurrentUserRole() {
        return currentUser != null ? currentUser.getRole() : null;
    }

    public static boolean isLoggedIn() {
        return isLoggedIn && currentUser != null;
    }

    public static void logout() {
        currentUser = null;
        isLoggedIn = false;
    }
}