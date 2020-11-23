public class MyStudent {
    private String empid;
    private String fname;
    private String lname;
    private String email;
    private String sex;

    MyStudent(){
        this.empid = "";
        this.fname = "";
        this.lname = "";
        this.email = "";
        this.sex = "";
    }

    MyStudent(String empid, String fname, String lname, String email, String sex){
        this.empid = empid;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.sex = sex;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Student{" +
                "empid='" + empid + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
