package datahandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import models.Notice;
import models.User;

public class CSVReader {

    public static ArrayList<User> readUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            BufferedReader reader
                    = new BufferedReader(new FileReader("database/users.csv"));
            String line;
            // Header Skip
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] data = line.split(",\\s*");

                String id = data[0].trim();
                String name = data[1].trim();
                String role = data[2].trim();
                String password = data[3].trim();
                String status = data[4].trim();
                String studentClass = data[5].trim();
                String section = data[6].trim();
                String email = data[7].trim();
                String phone = data[8].trim();

                User user = new User(id, name, role, password, status, studentClass, section, email, phone);
                users.add(user);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Unable to read users.csv");
        }
        return users;
    }

    public static ArrayList<Notice> readNotices() {

        ArrayList<Notice> notices = new ArrayList<>();

        try {

            BufferedReader reader
                    = new BufferedReader(new FileReader("database/notices.csv"));

            String line;

            // Header Skip
            reader.readLine();

            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split(",\\s*");

                if (data.length < 5) {
                    continue;
                }

                String noticeId = data[0].trim();
                String title = data[1].trim();
                String description = data[2].trim();
                String postedBy = data[3].trim();
                String date = data[4].trim();

                Notice notice = new Notice(
                        noticeId,
                        title,
                        description,
                        postedBy,
                        date
                );

                notices.add(notice);
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("Unable to read notices.csv");
        }

        return notices;
    }
}
