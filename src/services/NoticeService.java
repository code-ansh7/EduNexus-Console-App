package services;

import datahandler.CSVReader;
import java.util.ArrayList;
import models.Notice;
import utils.InputHelper;

public class NoticeService {

    public static void showAllNotices() {

        ArrayList<Notice> notices = CSVReader.readNotices();

        System.out.println();
        System.out.println("==========================================");
        System.out.println("            SCHOOL NOTICES");
        System.out.println("==========================================");

        if (notices.isEmpty()) {
            System.out.println();
            System.out.println("No notices available.");
            return;
        }

        for (Notice notice : notices) {

            System.out.println("------------------------------------------");
            System.out.println("Notice ID : " + notice.getNoticeId());
            System.out.println("Title     : " + notice.getTitle());
            System.out.println("Message   : " + notice.getDescription());
            System.out.println("Posted By : " + notice.getPostedBy());
            System.out.println("Date      : " + notice.getDate());
        }

        System.out.println("------------------------------------------");

        System.out.println();
        System.out.println("Press ENTER to Continue...");
        InputHelper.scanner.nextLine();
    }
}