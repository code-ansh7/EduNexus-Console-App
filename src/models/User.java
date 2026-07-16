package models;

      public class User {

        private String id;
        private String name;
        private String role;
        private String password;
        private String status;
        private String studentClass;
        private String section;
        private String email;
        private String phone;

        public User(String id, String name, String role, String password, String status, String studentClass, String section, String email, String phone) {

        this.id = id; 
        this.name =  name;
        this.role = role; 
        this.password  = password;
        this.status  = status;
        this.studentClass = studentClass;
        this.section = section;
        this.email = email;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public String getSection() {
        return section;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
 
       return phone;
    }

}