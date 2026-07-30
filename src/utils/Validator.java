package utils;

public class Validator {

    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) return false;
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(regex);
    }

    public static boolean isValidPhone(String phone) {
        if (phone == null || phone.isEmpty()) return false;
        if (phone.length() != 10) return false;
        for (char c : phone.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    public static boolean isValidPassword(String password) {
        if (password == null) return false;
        return password.length() >= 6;
    }

    public static boolean isValidClass(String cls) {
        if (cls == null || cls.isEmpty()) return false;
        try {
            int c = Integer.parseInt(cls);
            return c >= 1 && c <= 12;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidSection(String section) {
        if (section == null || section.isEmpty()) return false;
        section = section.toUpperCase();
        return section.length() == 1 && section.charAt(0) >= 'A' && section.charAt(0) <= 'Z';
    }

    public static boolean isValidRole(String role) {
        if (role == null) return false;
        role = role.toLowerCase();
        return role.equals("student") || role.equals("teacher") || role.equals("admin");
    }

    public static boolean isValidStatus(String status) {
        if (status == null) return false;
        status = status.toLowerCase();
        return status.equals("active") || status.equals("inactive");
    }

    public static boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }
}