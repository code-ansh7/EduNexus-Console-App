package datahandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import models.User;

public class CSVReader {
    public static ArrayList<User> readUsers() {
    ArrayList<User> users = new ArrayList<>();
    try {
        BufferedReader reader =
                new BufferedReader(new FileReader("database/users.csv"));
        String line;
        // Header Skip
        reader.readLine();
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            String id = data[0].trim();
            String name = data[1].trim();
            String role = data[2].trim();
            String password = data[3].trim();
            User user = new User(id, name, role, password);
            users.add(user);
        }
        reader.close();
    } catch (IOException e) {
        System.out.println("Unable to read users.csv");
    }
    return users;
}
}