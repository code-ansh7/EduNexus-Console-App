package models;

public class Homework {

    private String id;
    private String subject;
    private String description;
    private String dueDate;
    private String classAssigned;
    private String teacherId;
    private String datePosted;

    public Homework() {
    }

    public Homework(String id, String subject, String description, String dueDate,
                    String classAssigned, String teacherId, String datePosted) {
        this.id = id;
        this.subject = subject;
        this.description = description;
        this.dueDate = dueDate;
        this.classAssigned = classAssigned;
        this.teacherId = teacherId;
        this.datePosted = datePosted;
    }

    public static Homework fromCSV(String[] data) {
        if (data == null || data.length < 7) return null;
        return new Homework(
            data[0].trim(),
            data[1].trim(),
            data[2].trim(),
            data[3].trim(),
            data[4].trim(),
            data[5].trim(),
            data[6].trim()
        );
    }

    public String[] toCSV() {
        return new String[]{id, subject, description, dueDate, classAssigned, teacherId, datePosted};
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getDueDate() { return dueDate; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }

    public String getClassAssigned() { return classAssigned; }
    public void setClassAssigned(String classAssigned) { this.classAssigned = classAssigned; }

    public String getTeacherId() { return teacherId; }
    public void setTeacherId(String teacherId) { this.teacherId = teacherId; }

    public String getDatePosted() { return datePosted; }
    public void setDatePosted(String datePosted) { this.datePosted = datePosted; }

    @Override
    public String toString() {
        return "📚 " + subject + "\n   Description: " + description +
               "\n   Due: " + dueDate + "\n   Class: " + classAssigned;
    }
}