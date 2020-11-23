public class MyClass {
    private String courseid;
    private String empid;
    private String sectionNo;
    private String year;
    private String semester;
    private String grade;

    public MyClass(){
        this.courseid = "";
        this.empid = "";
        this.sectionNo = "";
        this.year = "";
        this.semester = "";
        this.grade = "";
    }

    public MyClass(String courseid, String empid, String sectionNo, String year, String semester, String grade) {
        this.courseid = courseid;
        this.empid = empid;
        this.sectionNo = sectionNo;
        this.year = year;
        this.semester = semester;
        this.grade = grade;
    }

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getSectionNo() {
        return sectionNo;
    }

    public void setSectionNo(String sectionNo) {
        this.sectionNo = sectionNo;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "MyClass{" +
                "courseid='" + courseid + '\'' +
                ", emplid='" + empid + '\'' +
                ", sectionNo='" + sectionNo + '\'' +
                ", year='" + year + '\'' +
                ", semester='" + semester + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}
