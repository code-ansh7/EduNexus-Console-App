package app;
import utils.ConsoleUI;
import datahandler.CSVReader;
public class Main{
    public static void main(String[] args) {
        ConsoleUI.showSplashScreen();
        CSVReader.readUsers();
        ConsoleUI.showWelcomeScreen();

    }
}