package models;

public class Notice {

    private String noticeId;
    private String title;
    private String description;
    private String postedBy;
    private String date;

    public Notice(String noticeId, String title, String description, String postedBy, String date) {

        this.noticeId = noticeId;
        this.title = title;
        this.description = description;
        this.postedBy = postedBy;
        this.date = date;

    }

    public String getNoticeId() {
        return noticeId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public String getDate() {
        return date;
    }
}