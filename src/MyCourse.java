public class MyCourse {
    private String courseid;
    private String courseTitle;
    private String department;

    public MyCourse(){
        this.courseid = "";
        this.courseTitle = "";
        this.department = "";
    }

    public MyCourse(String courseid, String courseTitle, String department) {
        this.courseid = courseid;
        this.courseTitle = courseTitle;
        this.department = department;
    }

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseid='" + courseid + '\'' +
                ", courseTitle='" + courseTitle + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
