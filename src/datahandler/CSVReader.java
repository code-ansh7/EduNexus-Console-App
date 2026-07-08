package datahandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {
    public static void readUsers() {
        try {
            BufferedReader reader =
                    new BufferedReader(new FileReader("database/users.csv"));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Unable to read users.csv");
        }
    }
}