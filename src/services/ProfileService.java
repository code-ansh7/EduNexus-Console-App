package services;

import models.User;
import utils.InputHelper;

public class ProfileService {

    public static void showProfile(User user) {

        System.out.println();
        System.out.println("========================================");
        System.out.println("            MY PROFILE");
        System.out.println("========================================");

        System.out.println("ID       : " + user.getId());
        System.out.println("Name     : " + user.getName());
        System.out.println("Role     : " + user.getRole());
        System.out.println("Status   : " + user.getStatus());
        System.out.println("Class    : " + user.getStudentClass());
        System.out.println("Section  : " + user.getSection());
        System.out.println("Email    : " + user.getEmail());
        System.out.println("Phone    : " + user.getPhone());

        System.out.println();
        System.out.println("Press ENTER to Continue...");
        InputHelper.scanner.nextLine();
    }

}