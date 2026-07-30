package models;

public class Notice {

    private String id;
    private String title;
    private String content;
    private String date;
    private String authorId;
    private String targetRole;

    public Notice() {
    }

    public Notice(String id, String title, String content, String date, String authorId, String targetRole) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.authorId = authorId;
        this.targetRole = targetRole;
    }

    public static Notice fromCSV(String[] data) {
        if (data == null || data.length < 6) return null;
        return new Notice(
            data[0].trim(),
            data[1].trim(),
            data[2].trim(),
            data[3].trim(),
            data[4].trim(),
            data[5].trim()
        );
    }

    public String[] toCSV() {
        return new String[]{id, title, content, date, authorId, targetRole};
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getAuthorId() { return authorId; }
    public void setAuthorId(String authorId) { this.authorId = authorId; }

    public String getTargetRole() { return targetRole; }
    public void setTargetRole(String targetRole) { this.targetRole = targetRole; }

    @Override
    public String toString() {
        return "📢 " + title + "\n   Date: " + date + "\n   " + content;
    }
}