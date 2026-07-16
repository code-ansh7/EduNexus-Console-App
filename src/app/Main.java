package app;

import datahandler.CSVReader;
import java.util.ArrayList;
import models.User;
import utils.ConsoleUI;

public class Main {

    public static void main(String[] args) {
        ConsoleUI.showSplashScreen();

        ArrayList<User> users = CSVReader.readUsers();
        for (User user : users) {
            System.out.println(user.getId() + " | " + user.getName() + " | " + user.getRole() + " | " + user.getPassword() + " | " + user.getStudentClass() + " | " + user.getSection() + " | " + user.getEmail() + " | " + user.getPhone());
        }

        ConsoleUI.showWelcomeScreen();

    }
}
